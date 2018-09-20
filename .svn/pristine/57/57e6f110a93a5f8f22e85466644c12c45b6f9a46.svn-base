/**
 *
 */
package com.idriveevs.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public abstract class EvsClientRequest extends ModelBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6124189816129063417L;
	
	protected Map<String, String> headers = new HashMap<String, String>();
	{
		headers.put(HttpHeaders.ACCEPT, "application/json");
	}
	
	public abstract Map<String, String> getHeader();
	
	
	

}
