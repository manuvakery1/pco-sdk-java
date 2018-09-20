/**
 *
 */
package com.idriveevs.crypto;

import com.idriveevs.base.EvsAPIResource;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsCryptoServiceBuilderFactoryImpl implements EvsCryptoServiceBuilderFactory<EvsCryptoService> {

	/* (non-Javadoc)
	 * @see com.idriveevs.crypto.EvsCryptoServiceBuilderFactory#build(java.lang.String)
	 */
	public EvsCryptoService build(final EvsAPIResource.EvsEncAlogrithm alogrithm, String encKey) {
		EvsCryptoService evsCryptoService = null;
		if(alogrithm == EvsAPIResource.EvsEncAlogrithm.AES){
			evsCryptoService = new AESCryptoUtil(encKey);
		}else{
			evsCryptoService = new DESCryptoUtil(encKey);
		}
		return evsCryptoService;
	}

	
	
	
	
	
	
	

}
