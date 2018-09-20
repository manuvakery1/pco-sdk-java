/**
 *
 */
package com.idriveevs.htttp.client;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpRequestExecutor;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class ApacheHttpClientFactory implements HttpClientFactory<ConnectionManagerAwareHttpClient> {

	private final ConnectionManagerFactory<HttpClientConnectionManager> cmFactory = new ApacheConnectionManagerFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idriveevs.htttp.client.HttpClientFactory#create(com.idriveevs.htttp.
	 * client.HttpClientSettings)
	 */
	public ConnectionManagerAwareHttpClient create(HttpClientSettings settings) {
		final HttpClientBuilder builder = HttpClients.custom();
		HttpClientConnectionManager cm = cmFactory.create(settings);
		HttpRequestExecutor requestExec = new HttpRequestExecutor();
		builder.setRequestExecutor(requestExec)
		.setConnectionManager(cm);
		EvsSdkHttpClient evsSdkHttpClient = new EvsSdkHttpClient(builder.build(), cm);
		IdleConnectionReaper.registerConnectionManager(cm);
		return evsSdkHttpClient;
	}

}
