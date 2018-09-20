/**
 *
 */
package com.idriveevs.model;

import java.io.InputStream;

import com.idriveevs.base.EvsAPIResource.EvsRequestMethod;
import com.idriveevs.base.EvsAPIResource.EvsRequestType;
import com.idriveevs.core.util.ValidationUtils;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsWebMultipartRequest extends EvsWebRequest {

	private static final String FILE_PARAM_NAME = "file";

	/**
	 * 
	 */
	private static final long serialVersionUID = -8072195999473231962L;

	private InputStream is;

	private String orginalFileName;

	/**
	 * 
	 */
	public EvsWebMultipartRequest(String url, EvsClientRequest request) {
		this(url, request, EvsRequestMethod.POST);
	}

	/**
	 * 
	 */
	public EvsWebMultipartRequest(String url, EvsClientRequest request, EvsRequestMethod requestMethod) {
		super();
		ValidationUtils.rejectNull(url, "Evs Resource URl cannot be empty");
		ValidationUtils.rejectNull(request, "request object cannot be empty");
		this.setUrl(url);
		this.setRequestType(EvsRequestType.MULTIPART);
		this.setRequestMethod(requestMethod);
		this.setHeaders(request.getHeader());
		this.setRequestParams(introspect(request));
		this.is = ((EvsMultipartClientRequest) request).getInputStream();
		this.orginalFileName = ((EvsMultipartClientRequest) request).getOriginalFileName();
	}

	/**
	 * @return the is
	 */
	public InputStream getIs() {
		return is;
	}

	/**
	 * @param is
	 *            the is to set
	 */
	public void setIs(InputStream is) {
		this.is = is;
	}

	/**
	 * @return the orginalFileName
	 */
	public String getOrginalFileName() {
		return orginalFileName;
	}

	/**
	 * @param orginalFileName
	 *            the orginalFileName to set
	 */
	public void setOrginalFileName(String orginalFileName) {
		this.orginalFileName = orginalFileName;
	}

	public String getFileParamName() {
		return FILE_PARAM_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idriveevs.model.EvsRequest#validateParams()
	 */
	@Override
	public void validateParams() {
		super.validateParams();
		ValidationUtils.rejectNull(this.is, "InputStream cannot be null");
		ValidationUtils.rejectNull(this.orginalFileName, "OrginalFileName cannot be null");
	}

}
