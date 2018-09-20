/**
 *
 */
package com.idriveevs.exception;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsClientException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8675332634736162075L;
	
	/**
	 * 
	 */
	public EvsClientException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public EvsClientException(String message, Throwable throwable) {
		super(message, throwable);
	}
	

}
