/**
 *
 */
package com.idriveevs.core.annotation;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
* <p>
 * This annotation defines the class as thread safe. This means that
 * no sequences of accesses (reads and writes to public fields, calls to public methods)
 * may put the object into an invalid state, regardless of the interleaving of those actions
 * by the runtime, and without requiring any additional synchronization or coordination on the
 * part of the caller.
 * <p>
 */
public @interface ThreadSafe {

}
