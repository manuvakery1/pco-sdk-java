/**
 *
 */
package com.idriveevs.model;

import com.idriveevs.base.EvsAPIResource.EvsRequestMethod;
import com.idriveevs.base.EvsAPIResource.EvsRequestType;
import com.idriveevs.core.util.ValidationUtils;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsWebStandardRequest extends EvsWebRequest {
	
	/**
	 * 
	 */
	public EvsWebStandardRequest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public EvsWebStandardRequest(String url, EvsClientRequest request) {
		this(url,request, EvsRequestMethod.POST);
	}
	
	/**
	 * 
	 */
	public EvsWebStandardRequest(String url, EvsClientRequest request, EvsRequestMethod requestMethod) {
		super();
		ValidationUtils.rejectNull(url, "Evs Resource URl cannot be empty");
		ValidationUtils.rejectNull(request, "request object cannot be empty");
		this.setUrl(url);
		this.setRequestType(EvsRequestType.STANDARD);
		this.setRequestMethod(requestMethod);
		this.setHeaders(request.getHeader());
		this.setRequestParams(introspect(request));
	}

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3950317052919474701L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idriveevs.model.EvsRequest#validateParams()
	 */
	@Override
	public void validateParams() {
		// TODO Auto-generated method stub
		super.validateParams();
	}

}
