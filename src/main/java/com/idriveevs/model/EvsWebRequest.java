/**
 *
 */
package com.idriveevs.model;

import java.util.Map;

import com.idriveevs.base.EvsAPIResource;
import com.idriveevs.core.util.ValidationUtils;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsWebRequest extends ModelBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6231628631937230351L;
	
	
	private String url;
	private Map<String, String> headers;
	private Map<String, String> requestParams;
	private EvsAPIResource.EvsRequestType requestType;
	private EvsAPIResource.EvsRequestMethod requestMethod;
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the headers
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}
	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	/**
	 * @return the requestParams
	 */
	public Map<String, String> getRequestParams() {
		return requestParams;
	}
	/**
	 * @param requestParams the requestParams to set
	 */
	public void setRequestParams(Map<String, String> requestParams) {
		this.requestParams = requestParams;
	}
	/**
	 * @return the requestType
	 */
	public EvsAPIResource.EvsRequestType getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(EvsAPIResource.EvsRequestType requestType) {
		this.requestType = requestType;
	}
	/**
	 * @return the requestMethod
	 */
	public EvsAPIResource.EvsRequestMethod getRequestMethod() {
		return requestMethod;
	}
	/**
	 * @param requestMethod the requestMethod to set
	 */
	public void setRequestMethod(EvsAPIResource.EvsRequestMethod requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	public void validateParams(){
		ValidationUtils.rejectNull(this.url, "Evs Resource URL cannot be null");
		ValidationUtils.rejectNull(this.requestType, "requestType cannot be null");
		ValidationUtils.rejectNull(this.requestMethod, "requestMethod cannot be null");
		ValidationUtils.rejectNull(this.requestParams, "requestParams cannot be null");
		ValidationUtils.rejectNull(this.headers, "requestHeaders cannot be null");
	}
	
	
	
	

}
