/**
 *
 */
package com.idriveevs.transfer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.idriveevs.exception.EvsClientException;
import com.idriveevs.exception.EvsServerException;
import com.idriveevs.model.EvsCompleteMultipartUploadResponse;
import com.idrivevs.listener.ProgressListener;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsUploadResponse {
	
	
	private EvsUploadObserverCallable evsUploadObserver;
	
	public boolean isDone() {
		return evsUploadObserver.isDone() ;
	}
	
	public boolean isCancelled() {
		return evsUploadObserver.isCancelled();
	}
	
	
	private boolean pollUpload(){
		while (!evsUploadObserver.isDone()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
		return true;
	}
	
	public EvsCompleteMultipartUploadResponse getResponse(){
		pollUpload();
		Future<EvsCompleteMultipartUploadResponse> future = evsUploadObserver.getFuture();
		try {
			return future.get();
		} catch (InterruptedException e) {
			throw new EvsClientException(e.getMessage(), e);
		} catch (ExecutionException e) {
			throw new EvsClientException(e.getMessage(), e);
		}
	}
	

	
	public void waitForCompletion() throws EvsClientException, EvsServerException {
		pollUpload();
		Future<EvsCompleteMultipartUploadResponse> future = evsUploadObserver.getFuture();
		try {
			future.get();
		} catch (InterruptedException e) {
			throw new EvsClientException(e.getMessage(), e);
		} catch (ExecutionException e) {
			throw new EvsClientException(e.getMessage(), e);
		}
	}
	
	

	
	

	
	public ProgressListener getProgress() {
		ProgressListener listner = evsUploadObserver.getListner();
		return listner;
	}

	
	
	
	public void pause() throws EvsClientException, EvsServerException{
		evsUploadObserver.abort();
	}
	
	public void abort() throws EvsClientException, EvsServerException {
		System.out.println("calling abort");
		evsUploadObserver.abort();
	}
	
	public String getUploadState() {
		// TODO Auto-generated method stub
		return evsUploadObserver.getListner().getUploadStatus();
	}

	/* (non-Javadoc)
	 * @see com.idriveevs.transfer.EvsUpload#registerObserver(com.idriveevs.transfer.EvsUploadObserverCallable)
	 */
	public void registerObserver(EvsUploadObserverCallable observer) {
		this.evsUploadObserver = observer;
		
	}

	
	
	

}
