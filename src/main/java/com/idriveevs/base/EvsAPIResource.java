/**
 *
 */
package com.idriveevs.base;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public abstract class EvsAPIResource {
	
	public static final String CHARSET = "UTF-8";

	/**
	 * resembles the http request type
	 * 
	 * @author Manukm
	 *
	 */
	public enum EvsRequestMethod {
		GET, PUT, POST, DELETE
	}

	/**
	 * 
	 * @author Manukm
	 *
	 */
	public enum EvsRequestType {
		STANDARD, MULTIPART
	}
	
	
	public enum EvsEncAlogrithm{
		AES, DES
	}

}
