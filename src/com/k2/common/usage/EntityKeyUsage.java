package com.k2.common.usage;

import java.io.Serializable;

import com.k2.common.writeEvents.ValidationException;

public class EntityKeyUsage<E,U> {
	
	@SuppressWarnings("unused")
	private Class<E> entityClass;
	private Serializable key;
	private ForeignKeyDefinition<E,U> fkDefinition;

	public EntityKeyUsage (Class<E> entityClass, Serializable key, ForeignKeyDefinition<E,U> fkDefinition) {
		this.entityClass = entityClass;
		this.key = key;
		this.fkDefinition = fkDefinition;
	}
	
	public void cascade() throws CascadeDeletionException, ValidationException {
		fkDefinition.setUsageKey(key).cascade();
		
	}
	
	

}
