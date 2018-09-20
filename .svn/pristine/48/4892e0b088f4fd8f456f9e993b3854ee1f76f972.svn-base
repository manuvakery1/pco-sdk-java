/**
 *
 */
package com.idriveevs.htttp.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.HttpClientConnectionManager;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class IdleConnectionReaper extends Thread {
	
	 /**
     * The period between invocations of the idle connection reaper.
     */
    private static final int PERIOD_MILLISECONDS = 1000 * 60 * 1;
    /**
     * The list of registered connection managers, whose connections
     * will be periodically checked and idle connections closed.
     */
    private static final ArrayList<HttpClientConnectionManager>
            connectionManagers = new ArrayList<HttpClientConnectionManager>();
    /**
     * Singleton instance of the connection reaper.
     */
    private static IdleConnectionReaper instance;
    /**
     * Set to true when shutting down the reaper;  Once set to true, this
     * flag is never set back to false.
     */
    private volatile boolean shuttingDown;

    /**
     * Private constructor - singleton pattern.
     */
    private IdleConnectionReaper() {
        super("java-sdk-http-connection-reaper");
        setDaemon(true);
    }

    /**
     * Registers the given connection manager with this reaper;
     *
     * @return true if the connection manager has been successfully registered;
     * false otherwise.
     */
    public static synchronized boolean registerConnectionManager(HttpClientConnectionManager connectionManager) {
        if (instance == null) {
            instance = new IdleConnectionReaper();
            instance.start();
        }
        return connectionManagers.add(connectionManager);
    }

    /**
     * Removes the given connection manager from this reaper,
     * and shutting down the reaper if there is zero connection manager left.
     *
     * @return true if the connection manager has been successfully removed;
     * false otherwise.
     */
    public static synchronized boolean removeConnectionManager(HttpClientConnectionManager connectionManager) {
        boolean b = connectionManagers.remove(connectionManager);
        if (connectionManagers.isEmpty())
            shutdown();
        return b;
    }

    public static synchronized List<HttpClientConnectionManager> getRegisteredConnectionManagers() {
        return Collections.unmodifiableList(connectionManagers);
    }

    /**
     * Shuts down the thread, allowing the class and instance to be collected.
     * <p/>
     * Since this is a daemon thread, its running will not prevent JVM shutdown.
     * It will, however, prevent this class from being unloaded or garbage
     * collected, in the context of a long-running application, until it is
     * interrupted. This method will stop the thread's execution and clear its
     * state. Any use of a service client will cause the thread to be restarted.
     *
     * @return true if an actual shutdown has been made; false otherwise.
     */
    public static synchronized boolean shutdown() {
        if (instance != null) {
            instance.markShuttingDown();
            instance.interrupt();
            connectionManagers.clear();
            instance = null;
            return true;
        }
        return false;
    }

    /**
     * For testing purposes.
     * Returns the number of connection managers currently monitored by this
     * reaper.
     */
    static synchronized int size() {
        return connectionManagers.size();
    }

    private void markShuttingDown() {
        shuttingDown = true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        while (true) {
            if (shuttingDown) {
                return;
            }
            try {
            	System.out.println("coming");
                Thread.sleep(PERIOD_MILLISECONDS);

                // Copy the list of managed ConnectionManagers to avoid possible
                // ConcurrentModificationExceptions if registerConnectionManager or
                // removeConnectionManager are called while we're iterating (rather
                // than block/lock while this loop executes).
                List<HttpClientConnectionManager> connectionManagers = null;
                synchronized (IdleConnectionReaper.class) {
                    connectionManagers = (List<HttpClientConnectionManager>) IdleConnectionReaper.connectionManagers.clone();
                }
                for (HttpClientConnectionManager connectionManager : connectionManagers) {
                    // When we release connections, the connection manager leaves them
                    // open so they can be reused.  We want to close out any idle
                    // connections so that they don't sit around in CLOSE_WAIT.
                    try {
                    	System.out.println("closing idle connection");
                        connectionManager.closeIdleConnections(60, TimeUnit.SECONDS);
                    } catch (Exception t) {
                    }
                }
            } catch (Throwable t) {
            }
        }
    }

}
