/**
 *
 */
package com.idriveevs.htttp.client;

import java.net.SocketException;

import javax.net.ssl.SSLProtocolException;

import org.apache.http.conn.HttpHostConnectException;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class HttpClientSettings {
	
	private long timeToLive;
	private int maxConnections;
	private boolean retryFailedRequests;
	private int retryCount;
	
	
	/**
	 * 
	 */
	public HttpClientSettings() {
		init();
	}
	
	/**
	 * <p>
	 * initialize {@link HttpClientSettings} instance with default values
	 * <p>
	 */
	private void init(){
		this.timeToLive = -1;
		this.maxConnections = 5;
		this.retryFailedRequests = false;
		this.retryCount = 3;
	}
	

	/**
	 * @return the timeToLive
	 */
	public long getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive the timeToLive to set
	 */
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * @return the maxConnections
	 */
	public int getMaxConnections() {
		return maxConnections;
	}

	/**
	 * @param maxConnections the maxConnections to set
	 */
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}
	
	
	

	/**
	 * @return the retryFailedRequests
	 */
	public boolean isRetryFailedRequests() {
		return retryFailedRequests;
	}

	/**
	 * @param retryFailedRequests the retryFailedRequests to set
	 */
	public void setRetryFailedRequests(boolean retryFailedRequests) {
		this.retryFailedRequests = retryFailedRequests;
	}

	/**
	 * @return the retryCount
	 */
	public int getRetryCount() {
		return retryCount;
	}

	/**
	 * @param retryCount the retryCount to set
	 */
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	
	
	
	
	/**
	 * 
	 * @param e
	 * @param retryCount
	 * @return
	 */
	public boolean shouldRetry(Exception e, int retryCount){
		if(retryFailedRequests && retryCount <= this.retryCount && isRetrybaleException(e)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	private boolean isRetrybaleException(Exception e){
		if(e instanceof HttpHostConnectException || 
				e instanceof SocketException || 
				e instanceof SSLProtocolException){
			return true;
		}
		return false;
	}

}
