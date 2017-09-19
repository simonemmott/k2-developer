package com.k2.common.util;

import javax.persistence.Entity;

import com.k2.common.identity.Identity;

public class EntityUtil {

	public EntityUtil() {
	}
	
	public static String getName(Object entity) {
		String entityName = entity.getClass().getAnnotation(Entity.class).name();
		if ((entityName==null)||("".equals(entityName))) {
			entityName = entity.getClass().getSimpleName();
		}
		return entityName;
	}
	
	public static String getIdentity(Object entity) {
		if (Identity.class.isAssignableFrom(entity.getClass())) {
			return ((Identity)entity).getIdentity();
		} else {
			return entity.toString();
		}
	}
	
	public static String getFullIdentity(Object entity) {
		if (Identity.class.isAssignableFrom(entity.getClass())) {
			return ((Identity)entity).getFullIdentity();
		} else {
			return entity.toString();
		}
	}
	
	public static String getEntityIdentity(Object entity) {
		return "Entity: "+getName(entity)+" Identity: "+getFullIdentity(entity);
	}

}
