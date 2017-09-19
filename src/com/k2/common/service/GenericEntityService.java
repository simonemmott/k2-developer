package com.k2.common.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.common.identity.ID;
import com.k2.common.sequences.Sequences;

@SuppressWarnings("rawtypes")
@Transactional
public abstract class GenericEntityService<E,K extends Serializable, B extends ServiceModel> implements EntityService<E,K,B>{
	
	@Autowired
	protected Sequences sequences;
	
	@Autowired
	protected ServiceContext serviceContext;
	
	protected abstract GenericDAO<E,K> getDAO();
	protected E prepareNewEntity(E entity, String sequenceName) { return prepareNewEntity(entity, sequenceName, null); }
	protected E prepareNewEntity(E entity, String sequenceName, EntityInitialValues<E> init) {
		if (ID.class.isAssignableFrom(entity.getClass())) { ((ID)entity).setID(sequences.getNextValue(sequenceName)); }
		if (init != null) { init.initialize(entity); }
		return entity;
	}
	
	protected abstract B nullBO();

	@Override
	public B fetch(K id) { return getBO(getDAO().fetch(id)); }
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public void insert(B bo) { getDAO().add((E) bo.getEntity()); }
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(B bo) { getDAO().remove((E) bo.getEntity()); }
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public void update(B bo) { getDAO().update((E) bo.getEntity()); }
	@Override
	@Transactional
	public abstract ServiceList<B> listAll();
	
	@Override
	@Transactional
	public B newBO() { return newBO(null); }

	@Override
	@Transactional
	public abstract B newBO(EntityInitialValues<E> init);
	@Override
	@Transactional
	public abstract B getBO(E entity);



}
