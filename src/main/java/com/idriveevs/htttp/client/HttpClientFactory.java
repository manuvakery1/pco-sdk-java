/**
 *
 */
package com.idriveevs.htttp.client;



/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public interface HttpClientFactory<T> {
	
	T create(HttpClientSettings settings);

}
