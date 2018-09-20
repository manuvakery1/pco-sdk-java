/**
 *
 */
package com.idriveevs.transfer;

import java.io.File;
import java.util.Iterator;

import com.idriveevs.model.EvsUploadPartRequest;
import com.idrivevs.listener.ProgressListener;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class UploadPartRequestIterator implements Iterator<EvsUploadPartRequest> {

	private final String uploadId;
	private final long optimalPartSize;
	private final File file;
	private int partNumber = 1;
	private long offset = 0;
	private long remainingBytes;
	private final int totalNumberOfParts;
	private final ProgressListener listener;

	/**
	 * @param uploadId
	 * @param optimalPartSize
	 * @param file
	 * @param partNumber
	 * @param offset
	 * @param remainingBytes
	 * @param totalNumberOfParts
	 */
	public UploadPartRequestIterator(String uploadId, long optimalPartSize, File file, ProgressListener listener) {
		super();
		this.uploadId = uploadId;
		this.optimalPartSize = optimalPartSize;
		this.file = file;
		this.totalNumberOfParts = (int) Math.ceil((double) this.remainingBytes / this.optimalPartSize);
		this.remainingBytes = file.length();
		this.listener = listener;
	}

	

	public int getTotalNumberOfParts() {
		return totalNumberOfParts;
	}
	
	

	

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public synchronized boolean hasNext() {
		return (remainingBytes > 0);
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public EvsUploadPartRequest next() {
		long partSize = Math.min(optimalPartSize, remainingBytes);
		EvsUploadPartRequest uploadPartRequest = new EvsUploadPartRequest();
		uploadPartRequest.setUploadId(uploadId);
		uploadPartRequest.setPartNo(partNumber++);
		uploadPartRequest.setFile(file);
		uploadPartRequest.setOffsetPosition(offset);
		uploadPartRequest.setPartSize(partSize);
		uploadPartRequest.setFileName(file.getName());
		uploadPartRequest.setListener(listener);
		offset += partSize;
		remainingBytes -= partSize;

		return uploadPartRequest;
	}
	
	

}
