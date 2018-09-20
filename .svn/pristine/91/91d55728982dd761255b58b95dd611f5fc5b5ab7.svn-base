/**
 *
 */
package com.idriveevs.htttp.client;

import java.util.concurrent.TimeUnit;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class ApacheConnectionManagerFactory implements ConnectionManagerFactory<HttpClientConnectionManager> {

	/* (non-Javadoc)
	 * @see com.idriveevs.htttp.client.ConnectionManagerFactory#create(com.idriveevs.htttp.client.HttpClientSettings)
	 */
	public HttpClientConnectionManager create(HttpClientSettings settings) {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(settings.getTimeToLive(), TimeUnit.SECONDS);
		connectionManager.setDefaultMaxPerRoute(settings.getMaxConnections());
		connectionManager.setMaxTotal(settings.getMaxConnections());
		return connectionManager;
	}

}
