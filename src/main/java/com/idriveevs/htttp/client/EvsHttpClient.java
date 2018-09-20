/**
 *
 */
package com.idriveevs.htttp.client;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.idriveevs.base.EvsAPIResource;
import com.idriveevs.base.EvsAPIResource.EvsRequestMethod;
import com.idriveevs.base.EvsAPIResource.EvsRequestType;
import com.idriveevs.core.util.ValidationUtils;
import com.idriveevs.exception.EvsClientException;
import com.idriveevs.model.EvsWebMultipartRequest;
import com.idriveevs.model.EvsWebRequest;
import com.idriveevs.model.EvsWebStandardRequest;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsHttpClient {
	
	private static final Logger LOGGER = Logger.getLogger(EvsHttpClient.class);
	
	private static final HttpClientFactory<ConnectionManagerAwareHttpClient> httpClientFactory = new
            ApacheHttpClientFactory();
	private ConnectionManagerAwareHttpClient httpClient;
	private final HttpClientSettings httpClientSettings;
	/**
	 * @param httpClientSettings
	 */
	public EvsHttpClient(HttpClientSettings httpClientSettings) {
		super();
		this.httpClientSettings = httpClientSettings;
		this.httpClient = httpClientFactory.create(this.httpClientSettings);
	}
	
	/**
	 * <p>
	 * Helper method for calling underlying httpClient execute method
	 * <p>
	 * @param request
	 * @return
	 */
	private HttpResponse _request(HttpRequestBase request, int retryCount){
		ValidationUtils.rejectNull(request, "Http Request Object Cannot be null");
		try {
			return httpClient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			throw new EvsClientException(e.getMessage(), e);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			if(httpClientSettings.shouldRetry(e, retryCount)){
				System.out.println("retry request "+retryCount);
				return _request(request, ++retryCount);
			}
			throw new EvsClientException(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			if(httpClientSettings.shouldRetry(e, retryCount)){
				System.out.println("retry request "+retryCount);
				return _request(request, ++retryCount);
			}
			throw new EvsClientException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * 
	 * @param url
	 * @param is
	 * @param requestParams
	 * @param requestHeaders
	 * @param requestType
	 * @param requestMethod
	 * @return
	 */
	public <T extends EvsWebRequest> HttpResponse request(T request){
		ValidationUtils.rejectNull(request, "request object cannot be null");
		request.validateParams();
		HttpRequestBase httpRequest = null;
		if(request.getRequestType() == EvsRequestType.STANDARD){
			httpRequest = prepareStandardHttpRequest((EvsWebStandardRequest) request);
		}else{
			httpRequest = prepareMultipartHttpRequest((EvsWebMultipartRequest) request);
		}
		return _request(httpRequest, 0);
	}
	
	
	/**
	 * 
	 * @param url
	 * @param requestParams
	 * @param requestHeaders
	 * @param requestMethod
	 * @return
	 */
	private HttpRequestBase prepareStandardHttpRequest(EvsWebStandardRequest request){
		HttpRequestBase httpRequestBase = null;
		if(request.getRequestMethod() ==  EvsRequestMethod.GET){
			httpRequestBase = prepareHttpGetRequest(request.getUrl(), request.getRequestParams(), request.getHeaders());
		}else{
			try {
				httpRequestBase = prepareHttPostRequest(request.getUrl(), request.getRequestParams(), request.getHeaders());
			} catch (UnsupportedEncodingException e) {
				throw new EvsClientException(e.getMessage(), e);
			}
		}
		return httpRequestBase;
	}
	
	/**
	 * 
	 * @param url
	 * @param requestParams
	 * @param requestHeaders
	 * @return
	 */
	private HttpRequestBase prepareHttpGetRequest(String url, Map<String, String> requestParams,Map<String, String> requestHeaders){
		HttpGet get = new HttpGet(url);
		setHeader(get, requestHeaders);
		return get;
	}
	
	/**
	 * 
	 * @param url
	 * @param requestParams
	 * @param requestHeaders
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private HttpRequestBase prepareHttPostRequest(String url, Map<String, String> requestParams,Map<String, String> requestHeaders) throws UnsupportedEncodingException{
		HttpPost post = new HttpPost(url);
		setHeader(post, requestHeaders);
		setJsonParams(post, requestParams);
		return post;
	}
	
	/**
	 * 
	 * @param url
	 * @param is
	 * @param requestParams
	 * @param requestHeaders
	 * @return
	 */
	private HttpRequestBase prepareMultipartHttpRequest(EvsWebMultipartRequest request){
		HttpPost post = new HttpPost(request.getUrl());
		setHeader(post, request.getHeaders());
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody(request.getFileParamName(), request.getIs(), ContentType.APPLICATION_OCTET_STREAM, request.getOrginalFileName());
		setTextBody(builder, request.getRequestParams());
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		return post;
	}
	
	/***
	 * 
	 * @param request
	 * @param headers
	 */
	private void setHeader(HttpRequestBase request, Map<String, String> headers){
		for(Map.Entry<String, String> entry : headers.entrySet()){
			request.addHeader(entry.getKey(), entry.getValue());
		}
	}
	
	
	/***
	 * 
	 * @param request
	 * @param headers
	 */
	private void setTextBody(MultipartEntityBuilder builder, Map<String, String> params){
		for(Map.Entry<String, String> entry : params.entrySet()){
			builder.addTextBody(entry.getKey(), entry.getValue());
		}
	}
	
	
	/**
	 * 
	 * @param post
	 * @param params
	 * @throws UnsupportedEncodingException
	 */
	private void setJsonParams(HttpPost post, Map<String, String> params) throws UnsupportedEncodingException{
		Gson gson = new Gson(); 
		String json = gson.toJson(params);
		StringEntity entity = new StringEntity(json, "UTF8");
		entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(entity);
	}
	
	
	
	/**
	 * 
	 * @param post
	 * @param params
	 * @throws UnsupportedEncodingException
	 */
	private void setNameValuePair(HttpPost post, Map<String, String> params) throws UnsupportedEncodingException{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for(Map.Entry<String, String> entry : params.entrySet()){
			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	}
	
	
	
	
	
	

}
