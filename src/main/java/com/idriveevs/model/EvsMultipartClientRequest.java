/**
 *
 */
package com.idriveevs.model;

import java.io.InputStream;
import java.util.Map;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public abstract class EvsMultipartClientRequest extends EvsClientRequest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idriveevs.model.EvsClientRequest#getHeader()
	 */
	public  abstract Map<String, String> getHeader();
	
	public abstract InputStream getInputStream();

	public abstract String getOriginalFileName();

}
