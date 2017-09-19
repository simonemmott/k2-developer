package com.k2.common.usage;


public class EmptyEntityUsage<E> extends AbsractEntityUsage<E> implements EntityUsage<E> {
	
	@Override
	public boolean hasNext() { return false; }

	@Override
	public EntityKeyUsage<E,?> next() { return null; }

	@Override
	public void cascade() {}

}
