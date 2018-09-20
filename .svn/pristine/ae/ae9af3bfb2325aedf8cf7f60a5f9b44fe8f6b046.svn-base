package com.idrivevs.listener;

/**
 * 
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class ProgressListnerImpl implements ProgressListener {
	
	
	
	public static enum EvsUplaodStatus{
		InProgress,
		Cancelled,
		Paused,
		Completed,
		Error
	}
	
	
	
	private long transferredBytes;
	private final long totalBytes;
	private EvsUplaodStatus uplaodStatus;
	
	public ProgressListnerImpl(long totalBytes) {
		this.transferredBytes = 0;
		this.totalBytes = totalBytes;
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.listner.ProgressListener#setTransferredBytes(long)
	 */
	public synchronized void updateTransferredBytes(long bytes) {
		this.transferredBytes = transferredBytes+bytes;
		
	}
	
	public synchronized long getTransferedBytes(){
		return this.transferredBytes;
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.listner.ProgressListener#getProgressInPercentage()
	 */
	public float getProgressInPercentage() {
		return ((float) transferredBytes / totalBytes) * 100;
	}
	
	public synchronized void updateUploadStatus(EvsUplaodStatus evsUplaodStatus ){
		this.uplaodStatus  = evsUplaodStatus; 
	}
	
	
	public String getUploadStatus(){
		return null != this.uplaodStatus ? this.uplaodStatus.name() : null;
	}
	
	
	
	
	

	

}
