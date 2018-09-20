package com.idrivevs.listener;

import com.idrivevs.listener.ProgressListnerImpl.EvsUplaodStatus;

/**
 * 
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public interface ProgressListener {
	/**
	 * 
	 * @param bytes
	 */
	void updateTransferredBytes(long bytes);
	
	/**
	 * 
	 * @return
	 */
	public long getTransferedBytes();
	
	/**
	 * 
	 * @param evsUplaodStatus
	 */
	void updateUploadStatus(EvsUplaodStatus evsUplaodStatus );
	
	/**
	 * 
	 * @return
	 */
	float getProgressInPercentage();
	
	/**
	 * 
	 * @return
	 */
	String getUploadStatus();
	
}
