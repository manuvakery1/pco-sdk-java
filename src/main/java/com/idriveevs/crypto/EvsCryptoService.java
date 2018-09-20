/**
 *
 */
package com.idriveevs.crypto;

import java.io.InputStream;

import javax.crypto.CipherInputStream;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public interface EvsCryptoService {
	
	/**
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public CipherInputStream getCipherStream( InputStream is) throws Exception;
	
	/**
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public CipherInputStream getDeCipherInputStream(InputStream is) throws Exception;

}
