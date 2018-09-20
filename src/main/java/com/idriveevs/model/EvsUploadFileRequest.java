/**
 *
 */
package com.idriveevs.model;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import com.idriveevs.base.EvsAPIResource.EvsEncAlogrithm;
import com.idrivevs.listener.ProgressListener;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsUploadFileRequest extends EvsMultipartClientRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1928539667039935847L;

	private File file;

	private InputStream is;

	private String fileName;

	private String fileCheckSum;

	private long fileSize;

	public String fileEvsPath;
	private boolean encryptionEnabled;
	private String encKey;
	private EvsEncAlogrithm encAlgorithm;

	private ProgressListener listener;

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
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
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileCheckSum
	 */
	public String getFileCheckSum() {
		return fileCheckSum;
	}

	/**
	 * @param fileCheckSum
	 *            the fileCheckSum to set
	 */
	public void setFileCheckSum(String fileCheckSum) {
		this.fileCheckSum = fileCheckSum;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the listener
	 */
	public ProgressListener getListener() {
		return listener;
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	public void setListener(ProgressListener listener) {
		this.listener = listener;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idriveevs.model.EvsClientRequest#getHeader()
	 */
	@Override
	public Map<String, String> getHeader() {
		// TODO Auto-generated method stub
		return headers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idriveevs.model.EvsMultipartClientRequest#getInputStream()
	 */
	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return this.is;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idriveevs.model.EvsMultipartClientRequest#getOriginalFileName()
	 */
	@Override
	public String getOriginalFileName() {
		// TODO Auto-generated method stub
		return this.fileName;
	}

}
