/**
 *
 */
package com.idriveevs.transfer;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.idriveevs.client.IdriveEvsClient;
import com.idriveevs.exception.EvsClientException;
import com.idriveevs.model.EvsCompleteMultipartUploadRequest;
import com.idriveevs.model.EvsCompleteMultipartUploadResponse;
import com.idriveevs.model.EvsUploadPartResponse;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsCompleteMultipartUploadCallable implements Callable<EvsCompleteMultipartUploadResponse> {
	
	
	private final IdriveEvsClient evsClient;
	
	private final String uploadId;
	
	private final EvsUploadObserverCallable uploadObserver;
	
	private final List<Future<EvsUploadPartResponse>> futures;
	
	
	
	/**
	 * @param evsClient
	 * @param uploadId
	 * @param uploadObserver
	 */
	public EvsCompleteMultipartUploadCallable(IdriveEvsClient evsClient, String uploadId,
			List<Future<EvsUploadPartResponse>> futures, EvsUploadObserverCallable uploadObserver) {
		super();
		this.evsClient = evsClient;
		this.uploadId = uploadId;
		this.uploadObserver = uploadObserver;
		this.futures = futures;
	}



	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public EvsCompleteMultipartUploadResponse call() throws Exception {
		waitForPartsUplaodsToComplete();
		EvsCompleteMultipartUploadRequest request = new EvsCompleteMultipartUploadRequest();
		request.setUploadId(uploadId);
		EvsCompleteMultipartUploadResponse completeMultipartFileUpload = evsClient.completeMultipartFileUpload(request);
		uploadObserver.uploadComplete();
		return completeMultipartFileUpload;
		
	}
	
	/**
	 * 
	 */
	private void waitForPartsUplaodsToComplete(){
		for (Future<EvsUploadPartResponse> future : futures) {
			try {
				future.get();
			} catch (InterruptedException e) {
				throw new EvsClientException(e.getMessage(), e);
			} catch (ExecutionException e) {
				throw new EvsClientException(e.getMessage(), e);
			}
		}
	}

}
