package com.k2.common.usage;

import java.io.Serializable;

public abstract class AbsractEntityUsage<E> implements EntityUsage<E> {
	
	protected Class<E> entityClass;
	protected Serializable key;

	@SuppressWarnings("unchecked")
	@Override
	public EntityUsage<E> setEntityClass(Class<?> entityClass) {
		try {
			this.entityClass = (Class<E>)entityClass;
		} catch (ClassCastException e) {
			throw new UsageError("The entity class set on an entity usage must match the entity class used to fix the generic types of AbstractEntityUsage");
		}
		return this;
	}

	@Override
	public EntityUsage<E> setEntityKey(Serializable key) {
		this.key = key;
		return this;
	}

	@Override
	public abstract boolean hasNext();

	@Override
	public abstract EntityKeyUsage<E,?> next();

}
