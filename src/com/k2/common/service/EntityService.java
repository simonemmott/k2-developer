package com.k2.common.service;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public interface EntityService<E,K extends Serializable, B extends ServiceModel> {

	public B newBO();
	public B newBO(EntityInitialValues<E> init);
	public B fetch(K id);
	public void insert(B bo);
	public void delete(B bo);
	public void update(B bo);
	public ServiceList<B> listAll();
	B getBO(E entity);
	

}
