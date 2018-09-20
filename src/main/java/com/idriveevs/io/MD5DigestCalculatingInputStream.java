/**
 *
 */
package com.idriveevs.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 * <p>
 * this class is used to calculate the md5 digest on the fly
 * it avoid loading the complete file in the memory
 */
public class MD5DigestCalculatingInputStream extends FilterInputStream {

	private MessageDigest digest;

	/**
	 * @param arg0
	 */
	public MD5DigestCalculatingInputStream(InputStream is) {
		super(is);
		this.digest = newMD5();
	}

	private MessageDigest newMD5() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) { // should never occur
			throw new IllegalStateException("unexpected", e);
		}
	}

	public byte[] getMd5Digest() {
		return digest.digest();
	}

	@Override
	public int read() throws IOException {
		int ch = super.read();
		if (ch != -1) {
			digest.update((byte) ch);
		}
		return ch;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int result = super.read(b, off, len);
		if (result != -1) {
			digest.update(b, off, result);
		}
		return result;
	}

}
