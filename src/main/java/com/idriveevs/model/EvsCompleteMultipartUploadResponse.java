/**
 *
 */
package com.idriveevs.model;


/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsCompleteMultipartUploadResponse extends EvsFileUploadInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4783421455428632522L;
	private String md5DigestHex;
	/**
	 * @return the md5DigestHex
	 */
	public String getMd5DigestHex() {
		return md5DigestHex;
	}
	/**
	 * @param md5DigestHex the md5DigestHex to set
	 */
	public void setMd5DigestHex(String md5DigestHex) {
		this.md5DigestHex = md5DigestHex;
	}
	
	
	
}
