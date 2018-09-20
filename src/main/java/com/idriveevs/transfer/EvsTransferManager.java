/**
 *
 */
package com.idriveevs.transfer;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.idriveevs.client.IdriveEvsClient;
import com.idriveevs.htttp.client.HttpClientSettings;
import com.idriveevs.model.EvsCompleteMultipartUploadRequest;
import com.idriveevs.model.EvsCompleteMultipartUploadResponse;
import com.idriveevs.model.EvsUploadPartResponse;
import com.idriveevs.model.EvsUserCredentials;
import com.idrivevs.listener.ProgressListener;
import com.idrivevs.listener.ProgressListnerImpl;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsTransferManager {
	
	private IdriveEvsClient evsClient;
	
	private ExecutorService executorService;
	
	private EvsConfigurations transferManagerSettings;
	
	
	/**
	 * 
	 */
	public EvsTransferManager(EvsUserCredentials evsUserCredentials) {
		this(evsUserCredentials, EvsTransferManagerUtil.createDefaultExecutorService());
	}
	
	/**
	 * 
	 */
	public EvsTransferManager(EvsUserCredentials evsUserCredentials,HttpClientSettings httpClientSettings ) {
		this(evsUserCredentials, EvsTransferManagerUtil.createDefaultExecutorService());
	}
	
	public EvsTransferManager(EvsUserCredentials evsUserCredentials, ExecutorService executorService) {
		this.evsClient = new IdriveEvsClient(evsUserCredentials);
		this.executorService = executorService;
		this.transferManagerSettings = new EvsConfigurations();
	}
	
	
	public EvsUploadResponse uploadFile(File file, String evsPath){
		return _doUpload(file, evsPath, null);
	}
	
	public EvsUploadResponse uploadFile(String uploadId, File file, String evsPath){
		return _doUpload(file, evsPath, uploadId);
	}
	
	private EvsUploadResponse _doUpload(File file, String evsPath, String uploadId){
		ProgressListener listener = new ProgressListnerImpl(file.length());
		EvsUploadCallable uploadCallable = new EvsUploadCallable(evsClient, executorService, uploadId, file, transferManagerSettings, evsPath, listener);
		EvsUploadObserverCallable uploadObserverCallable = new EvsUploadObserverCallable(uploadCallable, evsClient, executorService);
		EvsUploadResponse evsUploadResponse = new EvsUploadResponse();
		evsUploadResponse.registerObserver(uploadObserverCallable);
		return evsUploadResponse;
	}
	
	
	public void shutDown(){
		executorService.shutdownNow();
		evsClient = null;
	}
	
	
	
	
	
}
