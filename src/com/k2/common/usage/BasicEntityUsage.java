package com.k2.common.usage;

import java.util.LinkedList;

import com.k2.common.writeEvents.ValidationException;

public abstract class BasicEntityUsage<E> extends AbsractEntityUsage<E> implements EntityUsage<E> {
	
	protected LinkedList<ForeignKeyDefinition<?,?>> foreignKeys = new LinkedList<ForeignKeyDefinition<?,?>>();
	
	@Override
	public boolean hasNext() { return (!foreignKeys.isEmpty()); }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public EntityKeyUsage<E,?> next() {
		if (foreignKeys.isEmpty()) { return null; }
		return new EntityKeyUsage(entityClass, key, foreignKeys.removeFirst());
	}
	
	@Override
	public void cascade() throws CascadeDeletionException, ValidationException {
		while (hasNext()) {
			next().cascade();
		}
	}

}
