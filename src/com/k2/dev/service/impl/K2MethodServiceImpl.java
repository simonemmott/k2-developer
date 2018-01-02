package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.K2MethodDAO;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.bo.K2MethodBO;
import com.k2.dev.model.entity.K2MethodENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2MethodService;

@Service("K2MethodService")
@Transactional
public class K2MethodServiceImpl extends GenericEntityService<K2MethodENT, Long, K2Method> implements K2MethodService{

	public static class Lists {
		
		private static abstract class K2MethodServiceList extends GenericServiceList<K2MethodENT, K2Method> implements ServiceList<K2Method> {
			protected K2MethodDAO dao;
			protected K2MethodService service;
			public K2MethodServiceList(K2MethodService service, K2MethodDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.METHOD; }
		}

		public static class All extends K2MethodServiceList implements ServiceList<K2Method> {
			public All(K2MethodService service, K2MethodDAO dao) { super(service, dao); }
			@Override public K2Method newBO() { return service.newBO(); }
			@Override public K2Method newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2MethodENT> getList() { return dao.list(); }
			@Override protected K2Method getBO(K2MethodENT entity) { return service.getBO(entity); }
		}

		public static class ForEntity extends K2MethodServiceList implements ServiceList<K2Method> {
			private K2Entity k2Entity;
			public ForEntity(K2MethodService service, K2MethodDAO dao, K2Entity k2Entity) { 
				super(service, dao);
				this.k2Entity = k2Entity;
			}
			@Override public K2Method newBO() { return service.newBO(null, new Init.ForEntity(k2Entity)); }
			@Override public K2Method newBO(Long id) { return service.newBO(id, new Init.ForEntity(k2Entity)); }
			@Override protected List<K2MethodENT> getList() { return dao.listForEntity(k2Entity.getEntity()); }
			@Override protected K2Method getBO(K2MethodENT entity) { return service.getBO(entity); }
		}

	}

	public static class Init {
		
		public static class ForEntity extends EntityInitialValues<K2MethodENT> {
			private K2Entity k2Enity;
			public ForEntity(K2Entity k2Entity) {
				this.k2Enity = k2Entity;
			}
			@Override public void initialize(K2MethodENT k2Method) { 
				k2Method.setK2Entity(k2Enity.getEntity()); 
			}
		}
		
	}
	public K2MethodServiceImpl() {}
	
	@Autowired
	K2MethodDAO dao;
	@Override
	protected K2MethodDAO getDAO() { return dao; }
	@Override
	public K2Method nullBO() { return K2MethodBO.NULL; }
	
	@Override
	public ServiceList<K2Method> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public ServiceList<K2Method> listForEntity(K2Entity k2Entity) { return new Lists.ForEntity(this, dao, k2Entity); }

	
	@Override
	public K2Method newBO(Long id, EntityInitialValues<K2MethodENT> init) { 
		if (id == null) {
			return (K2Method) serviceContext.putBO(new K2MethodBO(prepareNewEntity(new K2MethodENT(), "K2Method.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2Method) serviceContext.putBO(new K2MethodBO(prepareNewEntity(new K2MethodENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public K2Method getBO(K2MethodENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2Method) serviceContext.getBO(entity); }
		return (K2Method) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2Method newK2Method() { return super.newBO(); }
	@Override
	public K2Method fetchK2Method(Long id) { return super.fetch(id); }


}
