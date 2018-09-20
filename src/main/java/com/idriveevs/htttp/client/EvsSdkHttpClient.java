/**
 *
 */
package com.idriveevs.htttp.client;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import com.idriveevs.core.util.ValidationUtils;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsSdkHttpClient implements ConnectionManagerAwareHttpClient {
	
	private final HttpClient httpClient;

    private final HttpClientConnectionManager cm;
    
    

	/**
	 * @param httpClient
	 * @param cm
	 */
	public EvsSdkHttpClient(HttpClient httpClient, HttpClientConnectionManager cm) {
		super();
		ValidationUtils.rejectNull(httpClient, "Http Client can not be null");
		ValidationUtils.rejectNull(cm, "Http Connection Manager can not be null");
		this.httpClient = httpClient;
		this.cm = cm;
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#execute(org.apache.http.client.methods.HttpUriRequest)
	 */
	public HttpResponse execute(HttpUriRequest arg0) throws IOException, ClientProtocolException {
		return httpClient.execute(arg0);
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#execute(org.apache.http.client.methods.HttpUriRequest, org.apache.http.protocol.HttpContext)
	 */
	public HttpResponse execute(HttpUriRequest arg0, HttpContext arg1) throws IOException, ClientProtocolException {
		return httpClient.execute(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#execute(org.apache.http.HttpHost, org.apache.http.HttpRequest)
	 */
	public HttpResponse execute(HttpHost arg0, HttpRequest arg1) throws IOException, ClientProtocolException {
		return httpClient.execute(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#execute(org.apache.http.client.methods.HttpUriRequest, org.apache.http.client.ResponseHandler)
	 */
	public <T> T execute(HttpUriRequest arg0, ResponseHandler<? extends T> arg1)
			throws IOException, ClientProtocolException {

		return httpClient.execute(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#execute(org.apache.http.HttpHost, org.apache.http.HttpRequest, org.apache.http.protocol.HttpContext)
	 */
	public HttpResponse execute(HttpHost arg0, HttpRequest arg1, HttpContext arg2)
			throws IOException, ClientProtocolException {
		return httpClient.execute(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#execute(org.apache.http.client.methods.HttpUriRequest, org.apache.http.client.ResponseHandler, org.apache.http.protocol.HttpContext)
	 */
	public <T> T execute(HttpUriRequest arg0, ResponseHandler<? extends T> arg1, HttpContext arg2)
			throws IOException, ClientProtocolException {
		return httpClient.execute(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#execute(org.apache.http.HttpHost, org.apache.http.HttpRequest, org.apache.http.client.ResponseHandler)
	 */
	public <T> T execute(HttpHost arg0, HttpRequest arg1, ResponseHandler<? extends T> arg2)
			throws IOException, ClientProtocolException {
		return httpClient.execute(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#execute(org.apache.http.HttpHost, org.apache.http.HttpRequest, org.apache.http.client.ResponseHandler, org.apache.http.protocol.HttpContext)
	 */
	public <T> T execute(HttpHost arg0, HttpRequest arg1, ResponseHandler<? extends T> arg2, HttpContext arg3)
			throws IOException, ClientProtocolException {
		return httpClient.execute(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#getConnectionManager()
	 */
	public ClientConnectionManager getConnectionManager() {
		// TODO Auto-generated method stub
		return httpClient.getConnectionManager();
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#getParams()
	 */
	public HttpParams getParams() {
		// TODO Auto-generated method stub
		return httpClient.getParams();
	}

	/* (non-Javadoc)
	 * @see com.idriveevs.htttp.apache.client.impl.ConnectionManagerAwareHttpClient#getHttpClientConnectionManager()
	 */
	public HttpClientConnectionManager getHttpClientConnectionManager() {
		// TODO Auto-generated method stub
		return cm;
	}

}
