/**
 *
 */
package com.idriveevs.transfer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.idriveevs.client.IdriveEvsClient;
import com.idriveevs.model.EvsAbortMultipartUploadRequest;
import com.idriveevs.model.EvsCompleteMultipartUploadResponse;
import com.idriveevs.model.EvsUploadPartResponse;
import com.idrivevs.listener.ProgressListener;
import com.idrivevs.listener.ProgressListnerImpl;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsUploadObserverCallable implements Callable<EvsCompleteMultipartUploadResponse> {

	private final EvsUploadCallable uploadCallable;
	private final IdriveEvsClient evsClient;
	private final ExecutorService executorService;

	private final List<Future<EvsUploadPartResponse>> futures = Collections
			.synchronizedList(new ArrayList<Future<EvsUploadPartResponse>>());

	private Future<EvsCompleteMultipartUploadResponse> future;

	private boolean isDone = false;
	
	private boolean isCancelled = false;

	public synchronized boolean isDone() {
		return isDone;
	}

	public synchronized boolean isCancelled() {
		return isCancelled || (uploadCallable.getProgressListner().getUploadStatus() == ProgressListnerImpl.EvsUplaodStatus.Cancelled.name());
	}

	public synchronized void markUploadComepleted() {
		this.isDone = true;
		uploadCallable.getProgressListner().updateUploadStatus(ProgressListnerImpl.EvsUplaodStatus.Completed);
	}

	public synchronized void markUplaodCancelled() {
		this.isCancelled = true;
		uploadCallable.getProgressListner().updateUploadStatus(ProgressListnerImpl.EvsUplaodStatus.Cancelled);
	}

	/**
	 * @param uploadCallable
	 * @param evsClient
	 */
	public EvsUploadObserverCallable(EvsUploadCallable uploadCallable, IdriveEvsClient evsClient,
			ExecutorService executorService) {
		super();
		this.uploadCallable = uploadCallable;
		this.evsClient = evsClient;
		this.executorService = executorService;
		executorService.submit(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	public EvsCompleteMultipartUploadResponse call() throws Exception {
		try {
			uploadCallable.call();
			futures.addAll(uploadCallable.getFutures());
			setFuture(executorService.submit(
					new EvsCompleteMultipartUploadCallable(evsClient, uploadCallable.getUploadId(), futures, this)));
		} catch (Exception e) {
			uploadCallable.cancelFutures();
			markUplaodCancelled();
		}
		
		return null;
	}

	void uploadComplete() {
		markUploadComepleted();
	}

	void pause() {
		uploadCallable.cancelFutures();
		markUplaodCancelled();
	}

	void abort() {
		uploadCallable.cancelFutures();
		EvsAbortMultipartUploadRequest request = new EvsAbortMultipartUploadRequest();
		request.setUploadId(uploadCallable.getUploadId());
		evsClient.abortMultipartFileUpload(request);
	}

	/**
	 * @return the future
	 */
	public synchronized Future<EvsCompleteMultipartUploadResponse> getFuture() {
		return future;
	}

	/**
	 * @param future
	 *            the future to set
	 */
	public synchronized void setFuture(Future<EvsCompleteMultipartUploadResponse> future) {
		this.future = future;
	}

	public ProgressListener getListner() {
		return this.uploadCallable.getProgressListner();
	}

}
