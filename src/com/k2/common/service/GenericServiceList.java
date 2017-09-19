package com.k2.common.service;

import java.util.Iterator;
import java.util.List;

public abstract class GenericServiceList<E,B> implements ServiceList<B> {
	
	private Iterator<E> iterator = null;
	private List<E> list = null;

	@Override
	public boolean hasNext() {
		if (list == null) { list = getList(); }
		if (iterator == null) { iterator = list.iterator(); }
		return iterator.hasNext();
	}

	@Override
	public B next() {
		if (list == null) { list = getList(); }
		if (iterator == null) { iterator = list.iterator(); }
		return getBO(iterator.next());
	}

	@Override
	public int count() { 
		if (list == null) { list = getList(); }
		return list.size();
	}

	@Override
	public void reset() {
		iterator = null;
		list = null;
	}
	
	protected abstract List<E> getList();
	protected abstract B getBO(E entity);

}
