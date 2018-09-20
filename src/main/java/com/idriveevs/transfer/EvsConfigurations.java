/**
 *
 */
package com.idriveevs.transfer;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsConfigurations {
	
	private long optimalPartSize = 1024 * 1024 * 5;
	
	private final long  singleUploadThrottle = 1024 * 1024 * 5;

	/**
	 * @return the optimalPartSize
	 */
	public long getOptimalPartSize() {
		return optimalPartSize;
	}

	/**
	 * @param optimalPartSize the optimalPartSize to set
	 */
	public void setOptimalPartSize(long optimalPartSize) {
		this.optimalPartSize = optimalPartSize;
	}
	
	/**
	 * @return the singleUploadThrottle
	 */
	public long getSingleUploadThrottle() {
		return singleUploadThrottle;
	}

}
