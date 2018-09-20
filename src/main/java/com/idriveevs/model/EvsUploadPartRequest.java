/**
 *
 */
package com.idriveevs.model;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import com.idriveevs.base.EvsAPIResource;
import com.idrivevs.listener.ProgressListener;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsUploadPartRequest extends EvsMultipartClientRequest {

	private File file;

	private InputStream is;

	private String fileName;

	private String uploadId;

	private int partNo;

	private String fileCheckSum;

	private long fileSize;

	private long partSize;

	private long offsetPosition;

	private String partCheckSum;
	
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
	 * @return the uploadId
	 */
	public String getUploadId() {
		return uploadId;
	}

	/**
	 * @param uploadId
	 *            the uploadId to set
	 */
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	/**
	 * @return the partNo
	 */
	public int getPartNo() {
		return partNo;
	}

	/**
	 * @param partId
	 *            the partNo to set
	 */
	public void setPartNo(int partNo) {
		this.partNo = partNo;
	}

	/**
	 * @return the partSize
	 */
	public long getPartSize() {
		return partSize;
	}

	/**
	 * @param partSize
	 *            the partSize to set
	 */
	public void setPartSize(long partSize) {
		this.partSize = partSize;
	}

	/**
	 * @return the offsetPosition
	 */
	public long getOffsetPosition() {
		return offsetPosition;
	}

	/**
	 * @param offsetPosition
	 *            the offsetPosition to set
	 */
	public void setOffsetPosition(long offsetPosition) {
		this.offsetPosition = offsetPosition;
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
	 * 
	 */
	private static final long serialVersionUID = 4873804724556601645L;

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

	/**
	 * @return the partCheckSum
	 */
	public String getPartCheckSum() {
		return partCheckSum;
	}

	/**
	 * @param partCheckSum
	 *            the partCheckSum to set
	 */
	public void setPartCheckSum(String partCheckSum) {
		this.partCheckSum = partCheckSum;
	}

	/**
	 * @return the listener
	 */
	public ProgressListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(ProgressListener listener) {
		this.listener = listener;
	}
	
	

}
