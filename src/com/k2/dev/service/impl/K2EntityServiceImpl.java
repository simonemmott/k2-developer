package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.K2EntityDAO;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.bo.K2EntityBO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.service.K2EntityService;

@Service("entityService")
@Transactional
public class K2EntityServiceImpl extends GenericEntityService<K2EntityENT, Long, K2Entity> implements K2EntityService{

	public static class Lists {
		private static abstract class K2EntityServiceList extends GenericServiceList<K2EntityENT, K2Entity> implements ServiceList<K2Entity> {
			protected K2EntityDAO dao;
			protected K2EntityService service;
			public K2EntityServiceList(K2EntityService service, K2EntityDAO dao) { this.service = service; this.dao = dao; }
		}

		public static class All extends K2EntityServiceList implements ServiceList<K2Entity> {
			public All(K2EntityService service, K2EntityDAO dao) { super(service, dao); }
			@Override public K2Entity newBO() { return service.newBO(); }
			@Override protected List<K2EntityENT> getList() { return dao.list(); }
			@Override protected K2Entity getBO(K2EntityENT entity) { return service.getBO(entity); }
		}
	}

	public K2EntityServiceImpl() {}

	@Autowired
	private K2EntityDAO dao;
	@Override
	protected K2EntityDAO getDAO() { return dao; }
	@Override
	public K2Entity nullBO() { return K2EntityBO.NULL; }
	
	@Override
	public ServiceList<K2Entity> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public K2Entity newBO(EntityInitialValues<K2EntityENT> init) {
		return (K2Entity) serviceContext.putBO(new K2EntityBO(prepareNewEntity(new K2EntityENT(), "Entity.ID", init), PersistenceState.NEW)); 
	}
	@Override
	public K2Entity getBO(K2EntityENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2Entity) serviceContext.getBO(entity); }
		return (K2Entity) serviceContext.putBO(new K2EntityBO(entity, PersistenceState.PERSISTED));
	}
	@Override
	public K2Entity newK2Entity() { return super.newBO(); }
	@Override
	public K2Entity fetchK2Entity(Long id) { return super.fetch(id); }
	@Override
	public K2Entity fetchForName(String name) { return getBO(dao.fetchForName(name)); }


}
