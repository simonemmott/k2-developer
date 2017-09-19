package com.k2.common.usage;

import java.io.Serializable;

import com.k2.common.writeEvents.ValidationException;


public interface EntityUsage<E> {

	public EntityUsage<E> setEntityClass(Class<?> entityClass);

	public EntityUsage<E> setEntityKey(Serializable key);
	
	public boolean hasNext();
	public EntityKeyUsage<E,?> next();
	public void cascade() throws CascadeDeletionException, ValidationException;

}
