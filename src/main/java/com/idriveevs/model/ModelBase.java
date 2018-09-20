/**
 *
 */
package com.idriveevs.model;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.idriveevs.exception.EvsClientException;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class ModelBase implements Serializable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	protected static Map<String, String> introspect(Object obj) {
		try {
			Map<String, String> result = new HashMap<String, String>();
			BeanInfo info = Introspector.getBeanInfo(obj.getClass());
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				Method reader = pd.getReadMethod();
				if (reader != null) {
					Object property = reader.invoke(obj);
					if (null != property) {
						if (property instanceof String) {
							result.put(pd.getName(), (String) property);
						} else if (property instanceof Long || property instanceof Integer
								|| property instanceof Boolean) {
							result.put(pd.getName(), String.valueOf(property));
						} else if (property.getClass().isEnum()) {
							result.put(pd.getName(), String.valueOf(property));
						}
					}

				}
			}
			return result.isEmpty() ? null : result;
		} catch (Exception e) {
			throw new EvsClientException(e.getMessage(), e);
		}

	}

}
