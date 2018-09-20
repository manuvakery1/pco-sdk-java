/**
 *
 */
package com.idriveevs.io;

import java.io.FileInputStream;
import java.io.InputStream;



/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class ReleasableInputStream extends EvsFilterInputStream implements Releasable {

	private boolean closeDisabled;

	protected ReleasableInputStream(InputStream is) {
		super(is);
	}

	/**
	 * If {@link #closeDisabled} is false, closes this input stream and releases
	 * any system resources associated with the stream. Otherwise, this method
	 * does nothing.
	 */
	@Override
	public final void close() {
		if (!closeDisabled)
			doRelease();
	}

	

	/**
	 * Used to truly release the underlying resources.
	 */
	private void doRelease() {
		try {
			in.close();
		} catch (Exception ex) {

		}
		if (in instanceof Releasable) {
			// This allows any underlying stream that has the close operation
			// disabled to be truly released
			Releasable r = (Releasable) in;
			r.release();
		}
	}

	/**
	 * Returns true if the close method has been disabled; false otherwise. Once
	 * the close method is disabled, caller would be responsible to release
	 * resources via {@link #release()}.
	 */
	public final boolean isCloseDisabled() {
		return closeDisabled;
	}

	/**
	 * Used to disable the close method. Once the close method is disabled,
	 * caller would be responsible to release resources via {@link #release()}.
	 */
	public final <T extends ReleasableInputStream> T disableClose() {
		this.closeDisabled = true;
		@SuppressWarnings("unchecked")
		T t = (T) this;
		return t;
	}

	/**
	 * Wraps the given input stream into a {@link ReleasableInputStream} if
	 * necessary.
	 */
	public static ReleasableInputStream wrap(InputStream is) {
		if (is instanceof ReleasableInputStream)
			return (ReleasableInputStream) is; // already wrapped
		 if (is instanceof FileInputStream)
	            return ResettableInputStream.newResettableInputStream((FileInputStream)is);
		return new ReleasableInputStream(is);
	}

	/* (non-Javadoc)
	 * @see com.idriveevs.io.Releasable#release()
	 */
	@Override
	public final void release() {
		doRelease();
		
	}

}
