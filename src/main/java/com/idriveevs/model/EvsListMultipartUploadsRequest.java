/**
 *
 */
package com.idriveevs.model;

import java.util.Map;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsListMultipartUploadsRequest extends EvsClientRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -574973153552589943L;
	
	//TODO add filter fields here

	/* (non-Javadoc)
	 * @see com.idriveevs.model.EvsClientRequest#getHeader()
	 */
	@Override
	public Map<String, String> getHeader() {
		// TODO Auto-generated method stub
		return headers;
	}

}
