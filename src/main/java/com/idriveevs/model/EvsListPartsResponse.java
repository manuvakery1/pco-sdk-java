/**
 *
 */
package com.idriveevs.model;

import java.util.List;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsListPartsResponse extends ModelBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6362124621162149495L;
	private String uploadId;
	private String fileName;
	private List<EvsUploadPartResponse> fileParts;
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
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileParts
	 */
	public List<EvsUploadPartResponse> getFileParts() {
		return fileParts;
	}
	/**
	 * @param fileParts the fileParts to set
	 */
	public void setFileParts(List<EvsUploadPartResponse> fileParts) {
		this.fileParts = fileParts;
	}
	
}
