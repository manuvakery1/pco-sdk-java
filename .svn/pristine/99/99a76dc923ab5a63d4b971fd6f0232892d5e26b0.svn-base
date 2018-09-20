/**
 *
 */
package com.idriveevs.core.util;

import com.idriveevs.core.annotation.ThreadSafe;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */

@ThreadSafe
public class ValidationUtils {
	
	
	/**
     * <p>
     * Asserts that the specified parameter value is not <code>null</code> and if it is,
     * throws an <code>IllegalArgumentException</code> with the specified error message.
     * </p>
     *
     * @param parameterValue
     *            The parameter value being checked.
     * @param errorMessage
     *            The error message to include in the IllegalArgumentException
     *            if the specified parameter is null.
     */
    public static void rejectNull(Object parameterValue, String errorMessage) {
        if (parameterValue == null) throw new IllegalArgumentException(errorMessage);
    }
	
}
