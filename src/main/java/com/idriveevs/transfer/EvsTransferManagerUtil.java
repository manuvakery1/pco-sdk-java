/**
 *
 */
package com.idriveevs.transfer;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsTransferManagerUtil {

	/**
	 * 
	 * @return
	 */
	public static ThreadPoolExecutor createDefaultExecutorService() {
		ThreadFactory threadFactory = new ThreadFactory() {
			private int threadCount = 1;

			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("idrive-evs-transfer-manager-worker-" + threadCount++);
				return thread;
			}
		};
		return (ThreadPoolExecutor) Executors.newFixedThreadPool(4, threadFactory);
	}
	
	
	

}
