/**
 *
 */
package com.idriveevs.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.idriveevs.base.EvsAPIResource;
import com.idriveevs.core.util.ValidationUtils;
import com.idriveevs.crypto.EvsCryptoService;
import com.idriveevs.crypto.EvsCryptoServiceBuilderFactory;
import com.idriveevs.crypto.EvsCryptoServiceBuilderFactoryImpl;
import com.idriveevs.exception.EvsClientException;
import com.idriveevs.exception.EvsServerException;
import com.idriveevs.htttp.client.EvsHttpClient;
import com.idriveevs.htttp.client.HttpClientSettings;
import com.idriveevs.io.EvsSubInputStream;
import com.idriveevs.io.MD5DigestCalculatingInputStream;
import com.idriveevs.model.EvsAbortMultipartUploadRequest;
import com.idriveevs.model.EvsAbortMultipartUploadResponse;
import com.idriveevs.model.EvsClientRequest;
import com.idriveevs.model.EvsCompleteMultipartUploadRequest;
import com.idriveevs.model.EvsCompleteMultipartUploadResponse;
import com.idriveevs.model.EvsInitiateMultipartUploadRequest;
import com.idriveevs.model.EvsInitiateMultipartUploadResponse;
import com.idriveevs.model.EvsListMultipartUploadsRequest;
import com.idriveevs.model.EvsListMultipartUploadsResponse;
import com.idriveevs.model.EvsListPartsRequest;
import com.idriveevs.model.EvsListPartsResponse;
import com.idriveevs.model.EvsResponse;
import com.idriveevs.model.EvsServerErrorResponse;
import com.idriveevs.model.EvsUploadFileRequest;
import com.idriveevs.model.EvsUploadFileResponse;
import com.idriveevs.model.EvsUploadPartRequest;
import com.idriveevs.model.EvsUploadPartResponse;
import com.idriveevs.model.EvsUserCredentials;
import com.idriveevs.model.EvsWebMultipartRequest;
import com.idriveevs.model.EvsWebRequest;
import com.idriveevs.model.EvsWebStandardRequest;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class IdriveEvsClient {

	private static final String LIVE_API_BASE = "http://192.168.1.137:8080";

	private final EvsHttpClient httpClient;
	private final static EvsCryptoServiceBuilderFactory<EvsCryptoService>  cryptoServiceBuilder = new EvsCryptoServiceBuilderFactoryImpl();
	private final EvsUserCredentials evsUserCredentials;
	/**
	 * 
	 */
	public IdriveEvsClient(EvsUserCredentials evsUserCredentials) {
		this(getDefaultHttpClientSettings(), evsUserCredentials);
	}

	/**
	 * @param httpClient
	 */
	public IdriveEvsClient(HttpClientSettings httpClientSettings, EvsUserCredentials evsUserCredentials) {
		super();
		ValidationUtils.rejectNull(evsUserCredentials, "user credentials cannot be empty");
		ValidationUtils.rejectNull(evsUserCredentials.getUserId(), "userId cannot be empty");
		ValidationUtils.rejectNull(evsUserCredentials.getPassword(), "password cannot be empty");
		this.httpClient = new EvsHttpClient(httpClientSettings);
		this.evsUserCredentials = evsUserCredentials;
	}

	/**
	 * 
	 * @return
	 */
	private static HttpClientSettings getDefaultHttpClientSettings() {
		return new HttpClientSettings();
	}

	/**
	 * <p>
	 * retrieve the details of all multipart uploads performed by the user
	 * <p>
	 * 
	 * @param request
	 * @return
	 */
	public EvsListMultipartUploadsResponse listAllMulitpartUploads(EvsListMultipartUploadsRequest request) {
		EvsListMultipartUploadsResponse initiateMultipartResponse = new EvsListMultipartUploadsResponse();
		return invoke(request, initiateMultipartResponse);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public EvsInitiateMultipartUploadResponse initiateMultipartFileUpload(EvsInitiateMultipartUploadRequest request) {
		EvsInitiateMultipartUploadResponse initiateMultipartResponse = new EvsInitiateMultipartUploadResponse();
		return invoke(request, initiateMultipartResponse);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public EvsCompleteMultipartUploadResponse completeMultipartFileUpload(EvsCompleteMultipartUploadRequest request) {
		EvsCompleteMultipartUploadResponse completeMultipartUploadRequest = new EvsCompleteMultipartUploadResponse();
		return invoke(request, completeMultipartUploadRequest);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public EvsAbortMultipartUploadResponse abortMultipartFileUpload(EvsAbortMultipartUploadRequest request) {
		EvsAbortMultipartUploadResponse abortMultipartUploadResponse = new EvsAbortMultipartUploadResponse();
		return invoke(request, abortMultipartUploadResponse);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public EvsListPartsResponse listParts(EvsListPartsRequest request) {
		EvsListPartsResponse listPartsResponse = new EvsListPartsResponse();
		return invoke(request, listPartsResponse);
	}
	
	
	public EvsUploadFileResponse uploadFile(EvsUploadFileRequest request){
		return _uploadStream(request, false, null, null);
	}
	
	/**
	 * 
	 * @param request
	 * @param encKey
	 * @param encAlogrithm
	 * @return
	 */
	public EvsUploadFileResponse uploadFile(EvsUploadFileRequest request, String encKey, EvsAPIResource.EvsEncAlogrithm encAlogrithm) {
		return _uploadStream(request, true, encKey, encAlogrithm);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("resource")
	public EvsUploadPartResponse uploadFilePart(EvsUploadPartRequest request) {
		return _uploadStream(request, false, null, null);
	}
	
	/**
	 * 
	 * @param request
	 * @param encKey
	 * @param encAlogrithm
	 * @return
	 */
	public EvsUploadPartResponse uploadFilePart(EvsUploadPartRequest request, String encKey, EvsAPIResource.EvsEncAlogrithm encAlogrithm) {
		return _uploadStream(request, true, encKey, encAlogrithm);
	}
	
	
	
	@SuppressWarnings("resource")
	private EvsUploadPartResponse _uploadStream(EvsUploadPartRequest request, boolean encryptPart, String encKey, EvsAPIResource.EvsEncAlogrithm encAlogrithm){

		ValidationUtils.rejectNull(request.getFile(), "File cannot be null");
		InputStream subInputStream = null;
		InputStream cipherStream = null;
		MD5DigestCalculatingInputStream digestIs = null;
		EvsUploadPartResponse evsUploadPartResponse = null;
		try {
			EvsUploadPartResponse uploadPartResponse = new EvsUploadPartResponse();
			InputStream inputStream = request.getInputStream();
			File file = request.getFile();
			long offsetPosition = request.getOffsetPosition();
			long partSize = request.getPartSize();
			if (inputStream == null) {
				if (file != null) {
					try {
						inputStream = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						throw new EvsClientException(e.getMessage(), e);
					}
				} else {
					throw new EvsClientException("the source stream or file cannot be empty");
				}
			}
			subInputStream = new EvsSubInputStream(inputStream, offsetPosition, partSize);
			if(encryptPart){
				ValidationUtils.rejectNull(encAlogrithm, "EncAlgorithm cannot be empty");
				ValidationUtils.rejectNull(encKey, "encKey cannot be empty");
				/*
				 * prepare the cipher stream using the client encryption key
				 */
				EvsCryptoService evsCryptoService = cryptoServiceBuilder.build(encAlogrithm, encKey);
				cipherStream = evsCryptoService.getCipherStream(subInputStream);
				cipherStream = subInputStream = digestIs = new MD5DigestCalculatingInputStream(cipherStream);
				request.setIs(cipherStream);
			}else{
				subInputStream = digestIs = new MD5DigestCalculatingInputStream(subInputStream);
				request.setIs(subInputStream);
			}
			evsUploadPartResponse = invoke(request, uploadPartResponse);
			request.setIs(inputStream);
			String partMd5DigestHex = new String(Hex.encodeHex(digestIs.getMd5Digest()));
			System.out.println("partMd5DigestHex===============>"+partMd5DigestHex);
			System.out.println("evsUploadPartResponse.getMd5DigestHex()=======>"+evsUploadPartResponse.getMd5DigestHex());
			
			if (!partMd5DigestHex.equalsIgnoreCase(evsUploadPartResponse.getMd5DigestHex())) {
				final String info = "uploadId: " + request.getUploadId() + ", partNumber: " + request.getPartNo()
						+ ", partSize: " + partSize;
				throw new EvsClientException(
						"Unable to verify integrity of data upload.  " + "Client calculated content hash (contentMD5: "
								+ partMd5DigestHex + " in hex) didn't match hash ("
								+ evsUploadPartResponse.getMd5DigestHex() + " in hex) calculated by evs Server.  "
								+ "You may need to delete the data stored in evs. " + "(" + info + ")");
			}
			request.getListener().updateTransferredBytes(partSize);
		} catch (Exception e) {
			throw new EvsClientException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(subInputStream);
			IOUtils.closeQuietly(cipherStream);
			IOUtils.closeQuietly(digestIs);
		}
		return evsUploadPartResponse;
	
	}
	
	
	@SuppressWarnings("resource")
	private EvsUploadFileResponse _uploadStream(EvsUploadFileRequest request, boolean encryptPart, String encKey, EvsAPIResource.EvsEncAlogrithm encAlogrithm){

		ValidationUtils.rejectNull(request.getFile(), "File cannot be null");
		InputStream cipherStream = null;
		MD5DigestCalculatingInputStream digestIs = null;
		EvsUploadFileResponse evsFileUploadResponse = null;
		InputStream inputStream = null;
		try {
			EvsUploadFileResponse uploadResponse = new EvsUploadFileResponse();
			inputStream = request.getIs();
			File file = request.getFile();
			if (inputStream == null) {
				if (file != null) {
					try {
						inputStream = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						throw new EvsClientException(e.getMessage(), e);
					}
				} else {
					throw new EvsClientException("the source stream or file cannot be empty");
				}
			}
			if(encryptPart){
				ValidationUtils.rejectNull(encAlogrithm, "EncAlgorithm cannot be empty");
				ValidationUtils.rejectNull(encKey, "encKey cannot be empty");
				/*
				 * prepare the cipher stream using the client encryption key
				 */
				EvsCryptoService evsCryptoService = cryptoServiceBuilder.build(encAlogrithm, encKey);
				cipherStream = evsCryptoService.getCipherStream(inputStream);
				cipherStream = inputStream = digestIs = new MD5DigestCalculatingInputStream(cipherStream);
				request.setIs(cipherStream);
			}else{
				inputStream = digestIs = new MD5DigestCalculatingInputStream(inputStream);
				request.setIs(inputStream);
			}
			evsFileUploadResponse = invoke(request, uploadResponse);
			request.setIs(inputStream);
			String partMd5DigestHex = new String(Hex.encodeHex(digestIs.getMd5Digest()));
			if (!partMd5DigestHex.equalsIgnoreCase(evsFileUploadResponse.getMd5DigestHex())) {
				final String info = "";
				throw new EvsClientException(
						"Unable to verify integrity of data upload.  " + "Client calculated content hash (contentMD5: "
								+ partMd5DigestHex + " in hex) didn't match hash ("
								+ evsFileUploadResponse.getMd5DigestHex() + " in hex) calculated by evs Server.  "
								+ "You may need to delete the data stored in evs. " + "(" + info + ")");
			}
		} catch (Exception e) {
			throw new EvsClientException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(cipherStream);
			IOUtils.closeQuietly(digestIs);
		}
		return null;
	
	}

	/**
	 * 
	 * @param request
	 * @param respobseObj
	 * @return
	 */
	private <T> T invoke(EvsClientRequest request, T respobseObj) {
		EvsWebRequest webtReqest = null;
		if (request instanceof EvsUploadPartRequest || request instanceof EvsUploadFileRequest) {
			webtReqest = new EvsWebMultipartRequest(resourceURL(request.getClass()), request);
		} else {
			webtReqest = new EvsWebStandardRequest(resourceURL(request.getClass()), request);
		}
		injectUserCredntials(webtReqest);
		HttpResponse httpResponse = httpClient.request(webtReqest);
		EvsResponse<T> reponse = processHttpReponse(httpResponse, respobseObj);
		return reponse.getResponse();
	}
	
	/**
	 * user credentials is mandatory for all requests
	 * @param webtReqest
	 */
	private void injectUserCredntials(EvsWebRequest webtReqest){
		webtReqest.getRequestParams().put("userId", evsUserCredentials.getUserId());
		webtReqest.getRequestParams().put("password", evsUserCredentials.getPassword());
	}

	/**
	 * 
	 * @param response
	 * @param _class
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	private <T> EvsResponse<T> processHttpReponse(HttpResponse response, T obj) {
		EvsResponse<T> evsReponse = new EvsResponse<T>();
		try {
			if (response.getStatusLine().getStatusCode() == 200) {
				InputStream content = response.getEntity().getContent();
				String json = IOUtils.toString(content);
				IOUtils.closeQuietly(content);
				obj = (T) new ObjectMapper().readValue(json, obj.getClass());
				evsReponse.setResponse(obj);
			} else {
				InputStream content = response.getEntity().getContent();
				String json = IOUtils.toString(content);
				IOUtils.closeQuietly(content);
				EvsServerErrorResponse errorResponse = (EvsServerErrorResponse) new ObjectMapper().readValue(json,
						EvsServerErrorResponse.class);
				throw new EvsServerException(errorResponse);
			}

		} catch (JsonParseException e) {
			throw new EvsClientException(e.getMessage(), e);
		} catch (JsonMappingException e) {
			throw new EvsClientException(e.getMessage(), e);
		} catch (IOException e) {
			throw new EvsClientException(e.getMessage(), e);
		}

		return evsReponse;
	}

	/**
	 * each classname identifies a unique idrive evs resource
	 * 
	 * @param clazz
	 * @return
	 */
	private static String resourceName(Class<?> clazz) {
		String className = clazz.getSimpleName().replace("$", " ");
		if (className.equalsIgnoreCase("EvsInitiateMultipartUploadRequest")) {
			return "multipartupload/initiate";
		} else if (className.equalsIgnoreCase("EvsCompleteMultipartUploadRequest")) {
			return "multipartupload/complete";
		} else if (className.equalsIgnoreCase("EvsAbortMultipartUploadRequest")) {
			return "multipartupload/abort";
		} else if (className.equalsIgnoreCase("EvsListPartsRequest")) {
			return "multipartupload/listparts";
		} else if (className.equalsIgnoreCase("EvsUploadPartRequest")) {
			return "multipartupload/uploadpart";
		} else if (className.equalsIgnoreCase("EvsListMultipartUploadsRequest")) {
			return "multipartupload/list";
		} else if(className.equalsIgnoreCase("EvsUploadFileRequest")){
			return "fileupload";
		}else{
			throw new EvsClientException("resource not available");
		}
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	private static String resourceURL(Class<?> clazz) {
		return buildResourceURL(clazz, LIVE_API_BASE);
	}

	/**
	 * 
	 * @param clazz
	 * @param apiBase
	 * @return
	 */
	private static String buildResourceURL(Class<?> clazz, String apiBase) {
		return String.format("%s/api/%s", apiBase, resourceName(clazz));
	}

}
