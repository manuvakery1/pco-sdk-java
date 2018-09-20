/**
 *
 */
package com.idriveevs.model;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsMultipartUpload extends ModelBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2289663609393603071L;
	
	private String uploadId;
	private String fileName;
	private String fileEvsPath;
	private String timeStamp;
	private String fileUploadStatus;
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
	 * @return the fileEvsPath
	 */
	public String getFileEvsPath() {
		return fileEvsPath;
	}
	/**
	 * @param fileEvsPath the fileEvsPath to set
	 */
	public void setFileEvsPath(String fileEvsPath) {
		this.fileEvsPath = fileEvsPath;
	}
	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * @return the fileUploadStatus
	 */
	public String getFileUploadStatus() {
		return fileUploadStatus;
	}
	/**
	 * @param fileUploadStatus the fileUploadStatus to set
	 */
	public void setFileUploadStatus(String fileUploadStatus) {
		this.fileUploadStatus = fileUploadStatus;
	}
	
	

}
