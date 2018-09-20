/**
 * 
 */
package com.idriveevs.io;

import java.io.IOException;
import java.io.InputStream;

import com.idriveevs.exception.EvsClientException;






/**
 * @author Manukm
 *
 */
public class EvsSubInputStream extends EvsFilterInputStream {
	
	private static final int MAX_SKIPS = 100;
    private long currentPosition;
    private final long requestedOffset;
    private final long requestedLength;
    private long markedPosition = 0;
	

	/**
	 * 
	 * @param in
	 * @param offset
	 * @param length
	 * @param closeSourceStream
	 */
	public EvsSubInputStream(InputStream in,long offset, long length) {
		super(in);
		this.requestedOffset = offset;
		this.requestedLength = length;
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.io.EvsFilterInputStream#available()
	 */
	@Override
	public int available() throws IOException {
		long bytesRemaining;
        if (currentPosition < requestedOffset)
            bytesRemaining = requestedLength;
        else {
            bytesRemaining =
                (requestedLength + requestedOffset) - currentPosition;
        }

        return (int) Math.min(bytesRemaining, super.available());
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.io.EvsFilterInputStream#close()
	 */
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		super.close();
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.io.EvsFilterInputStream#mark(int)
	 */
	@Override
	public synchronized void mark(int readlimit) {
		markedPosition = currentPosition;
		super.mark(readlimit);
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.io.EvsFilterInputStream#markSupported()
	 */
	@Override
	public boolean markSupported() {
		// TODO Auto-generated method stub
		return super.markSupported();
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.io.EvsFilterInputStream#read()
	 */
	@Override
	public int read() throws IOException {
		 byte[] b = new byte[1];
	        int bytesRead = read(b, 0, 1);

	        if (bytesRead == -1)
	            return bytesRead;
	        return b[0];
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.io.EvsFilterInputStream#read(byte[], int, int)
	 */
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int count = 0;
        while (currentPosition < requestedOffset) {
            long skippedBytes = super.skip(requestedOffset - currentPosition);
            if (skippedBytes == 0) {
                count++;
                if (count > MAX_SKIPS) {
                    throw new EvsClientException(
                            "Unable to position the currentPosition from "
                                    + currentPosition + " to "
                                    + requestedOffset);
                }
            }
            currentPosition += skippedBytes;
        }

        long bytesRemaining =
            (requestedLength + requestedOffset) - currentPosition;
        if (bytesRemaining <= 0)
            return -1;

        len = (int) Math.min(len, bytesRemaining);
        int bytesRead = super.read(b, off, len);
        currentPosition += bytesRead;

        return bytesRead;
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.io.EvsFilterInputStream#read(byte[])
	 */
	@Override
	public int read(byte[] b) throws IOException {
		return super.read(b);
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.io.EvsFilterInputStream#reset()
	 */
	@Override
	public synchronized void reset() throws IOException {
		currentPosition = markedPosition;
		super.reset();
	}

	/* (non-Javadoc)
	 * @see com.idrivevs.io.EvsFilterInputStream#skip(long)
	 */
	@Override
	public long skip(long n) throws IOException {
		return super.skip(n);
	}

}
