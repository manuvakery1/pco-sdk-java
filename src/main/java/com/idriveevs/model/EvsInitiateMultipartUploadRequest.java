/**
 *
 */
package com.idriveevs.model;

import java.util.Map;

import com.idriveevs.base.EvsAPIResource.EvsEncAlogrithm;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsInitiateMultipartUploadRequest extends EvsClientRequest {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4153187401023999613L;
	
	public String fileName;
	public Long fileSize;
	public String fileCheckSum;
	public String fileEvsPath;
	private boolean encryptionEnabled;
	private String encKey;
	private EvsEncAlogrithm encAlgorithm;
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
	 * @return the fileSize
	 */
	public Long getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the fileCheckSum
	 */
	public String getFileCheckSum() {
		return fileCheckSum;
	}
	/**
	 * @param fileCheckSum the fileCheckSum to set
	 */
	public void setFileCheckSum(String fileCheckSum) {
		this.fileCheckSum = fileCheckSum;
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
	 * @return the encryptionEnabled
	 */
	public boolean isEncryptionEnabled() {
		return encryptionEnabled;
	}
	/**
	 * @param encryptionEnabled the encryptionEnabled to set
	 */
	public void setEncryptionEnabled(boolean encryptionEnabled) {
		this.encryptionEnabled = encryptionEnabled;
	}
	/**
	 * @return the encKey
	 */
	public String getEncKey() {
		return encKey;
	}
	/**
	 * @param encKey the encKey to set
	 */
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}
	/**
	 * @return the encAlgorithm
	 */
	public EvsEncAlogrithm getEncAlgorithm() {
		return encAlgorithm;
	}
	/**
	 * @param encAlgorithm the encAlgorithm to set
	 */
	public void setEncAlgorithm(EvsEncAlogrithm encAlgorithm) {
		this.encAlgorithm = encAlgorithm;
	}
	/* (non-Javadoc)
	 * @see com.idriveevs.model.EvsClientRequest#getHeader()
	 */
	@Override
	public Map<String, String> getHeader() {
		return headers;
	}

}
