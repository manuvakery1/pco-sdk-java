/**
 *
 */
package com.idriveevs.exception;

import com.idriveevs.model.EvsServerErrorResponse;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsServerException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4324413612737702692L;
	private int status;
	private long timestamp;
	private String error;
	private String exception;
	private String message;
	private String path;
	
	public EvsServerException(String message){
		super(message);
	}

	/**
	 * @param status
	 * @param timestamp
	 * @param error
	 * @param exception
	 * @param message
	 * @param path
	 */
	public EvsServerException(EvsServerErrorResponse response) {
		super(response.getMessage());
		this.status = response.getStatus();
		this.timestamp = response.getTimestamp();
		this.error = response.getError();
		this.exception = response.getException();
		this.message = response.getMessage();
		this.path = response.getPath();
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EvsServerException [status=" + status + ", timestamp=" + timestamp + ", error=" + error + ", exception="
				+ exception + ", message=" + message + ", path=" + path + "]";
	}
	
	

}
