/**
 *
 */
package com.idriveevs.model;

import java.util.Map;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsListPartsRequest extends EvsClientRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1378237654029284982L;
	
	private String uploadId;

	/**
	 * @return the uploadId
	 */
	public String getUploadId() {
		return uploadId;
	}

	/**
	 * @param uploadId the uploadId to set
	 */
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	/* (non-Javadoc)
	 * @see com.idriveevs.model.EvsClientRequest#getHeader()
	 */
	@Override
	public Map<String, String> getHeader() {
		// TODO Auto-generated method stub
		return headers;
	}

}
