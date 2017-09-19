package com.k2.common.usage;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import com.k2.common.util.ClassUtil;

@Component
public class EntityMethodCache {
	
	Map<Class<?>, Map<String, Method>> setterMethods = new HashMap<Class<?>, Map<String, Method>>();
	Map<Class<?>, Map<String, Method>> getterMethods = new HashMap<Class<?>, Map<String, Method>>();
	
	private Method getMethod(Map<Class<?>, Map<String, Method>> methods, Class<?> entityClass, String field) {
		if (methods.containsKey(entityClass)) {
			Map<String, Method> entityMethodMap = setterMethods.get(entityClass);
			if (entityMethodMap.containsKey(field)) {
				return entityMethodMap.get(field);
			} else {
				BeanWrapper wrapper = new BeanWrapperImpl(entityClass);
				Method setter = wrapper.getPropertyDescriptor(field).getWriteMethod();
				if (setter == null) { throw new MethodCacheError("Unable to locate the setter method for the field: "+field+" on the entity: "+ClassUtil.getEntityName(entityClass)); }
				entityMethodMap.put(field, setter);
				return setter;
			}
		} else {
			Map<String, Method> entityMethodMap = new HashMap<String, Method>();
			methods.put(entityClass, entityMethodMap);
			BeanWrapper wrapper = new BeanWrapperImpl(entityClass);
			Method setter = wrapper.getPropertyDescriptor(field).getWriteMethod();
			if (setter == null) { throw new MethodCacheError("Unable to locate the setter method for the field: "+field+" on the entity: "+ClassUtil.getEntityName(entityClass)); }
			entityMethodMap.put(field, setter);
			return setter;
		}
		
	}

	public Method getSetterMethod(Class<?> entityClass, String field) {
		return getMethod(setterMethods, entityClass, field);		
	}

	public Method getGetterMethod(Class<?> entityClass, String field) {
		return getMethod(getterMethods, entityClass, field);		
	}

}
