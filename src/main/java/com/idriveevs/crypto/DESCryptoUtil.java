/**
 *
 */
package com.idriveevs.crypto;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class DESCryptoUtil implements EvsCryptoService {
	


	private static final String ALG = "DES";

	SecretKeySpec secretKeySpec;

	public DESCryptoUtil(String str) {
		setKey(str); 
	}

	/**
	 * ���ݲ������� KEY
	 */
	public void setKey(String strKey) {
		try {
			byte[] key = strKey.getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 8); // use only first 56 bit
			secretKeySpec = new SecretKeySpec(key, ALG);
		} catch (Exception e) {
			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idriveevs.crypto.EvsCryptoService#encryptFile(java.io.InputStream)
	 */
	public CipherInputStream getCipherStream(InputStream is) throws Exception {
		Cipher cipher = Cipher.getInstance(ALG);
		cipher.init(Cipher.ENCRYPT_MODE, this.secretKeySpec);
		return new CipherInputStream(is, cipher);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idriveevs.crypto.EvsCryptoService#getDeCipherInputStream(java.io.
	 * InputStream)
	 */
	public CipherInputStream getDeCipherInputStream(InputStream is) throws Exception {
		Cipher cipher = Cipher.getInstance(ALG);
		cipher.init(Cipher.DECRYPT_MODE, this.secretKeySpec);
		return new CipherInputStream(is, cipher);
	}


}
