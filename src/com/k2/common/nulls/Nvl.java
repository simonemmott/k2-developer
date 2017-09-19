package com.k2.common.nulls;

public class Nvl<E> {
	
	@SuppressWarnings({ "unchecked" })
	public E nvl(E entity, E valueIfNull, Fetcher<E> ... fetchers) {
		if (entity != null) { return entity; }
		for (Fetcher<E> fetcher : fetchers) {
			E fetched = fetcher.fetch();
			if (fetched != null) {
				return fetched;
			}
		}
		return valueIfNull;
	}

	@SuppressWarnings("unchecked") 
	public E nvl(E entity, Fetcher<E> ... fetchers) {
		return nvl(entity, (E)null, fetchers);
	}

	public E nvl(E entity, E valueIfNull) {
		if (entity != null) { return entity; }
		return valueIfNull;
	}

}
