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
import com.k2.dev.dao.K2EntityDAO;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.K2EntityBO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.impl.K2FieldServiceImpl.Lists;

@Service("K2EntityService")
@Transactional
public class K2EntityServiceImpl extends GenericEntityService<K2EntityENT, Long, K2Entity> implements K2EntityService{

	public static class Lists {
		private static abstract class K2EntityServiceList extends GenericServiceList<K2EntityENT, K2Entity> implements ServiceList<K2Entity> {
			protected K2EntityDAO dao;
			protected K2EntityService service;
			public K2EntityServiceList(K2EntityService service, K2EntityDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2ENTITY; }
		}

		public static class All extends K2EntityServiceList implements ServiceList<K2Entity> {
			public All(K2EntityService service, K2EntityDAO dao) { super(service, dao); }
			@Override public K2Entity newBO() { return service.newBO(); }
			@Override public K2Entity newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2EntityENT> getList() { return dao.list(); }
			@Override protected K2Entity getBO(K2EntityENT entity) { return service.getBO(entity); }
		}

		public static class ForService extends K2EntityServiceList implements ServiceList<K2Entity> {
			private K2Service k2Service;
			public ForService(K2EntityService service, K2EntityDAO dao, K2Service k2Service) { 
				super(service, dao);
				this.k2Service = k2Service;
			}
			@Override public K2Entity newBO() { return service.newBO(null, new Init.ListForService(k2Service.getEntity())); }
			@Override public K2Entity newBO(Long id) { return service.newBO(id, new Init.ListForService(k2Service.getEntity())); }
			@Override protected List<K2EntityENT> getList() { return dao.listForService(k2Service.getEntity()); }
			@Override protected K2Entity getBO(K2EntityENT entity) { return service.getBO(entity); }
		}

		public static class SubEntitiesForEntity extends K2EntityServiceList implements ServiceList<K2Entity> {
			private K2Entity k2Entity;
			public SubEntitiesForEntity(K2EntityService service, K2EntityDAO dao, K2Entity k2Entity) { 
				super(service, dao);
				this.k2Entity = k2Entity;
			}
			@Override public K2Entity newBO() { return service.newBO(null, new Init.ListSubEntitiesForEntity(k2Entity.getEntity())); }
			@Override public K2Entity newBO(Long id) { return service.newBO(id, new Init.ListSubEntitiesForEntity(k2Entity.getEntity())); }
			@Override protected List<K2EntityENT> getList() { return dao.listSubEntitiesForEntity(k2Entity.getEntity()); }
			@Override protected K2Entity getBO(K2EntityENT entity) { return service.getBO(entity); }
		}

		public static class RootEntitiesForService extends K2EntityServiceList implements ServiceList<K2Entity> {
			private K2Service k2Service;
			public RootEntitiesForService(K2EntityService service, K2EntityDAO dao, K2Service k2Service) { 
				super(service, dao);
				this.k2Service = k2Service;
			}
			@Override public K2Entity newBO() { return service.newBO(null, new Init.ListRootEntitiesForService(k2Service.getEntity())); }
			@Override public K2Entity newBO(Long id) { return service.newBO(id, new Init.ListRootEntitiesForService(k2Service.getEntity())); }
			@Override protected List<K2EntityENT> getList() { return dao.listRootEntitiesForService(k2Service.getEntity()); }
			@Override protected K2Entity getBO(K2EntityENT entity) { return service.getBO(entity); }
		}

	}

	public static class Init {
		
		public static class ListForService extends EntityInitialValues<K2EntityENT> {
			private K2ServiceENT k2Service;
			public ListForService(K2ServiceENT k2Service) {
				this.k2Service = k2Service;
			}
			@Override public void initialize(K2EntityENT entity) { entity.setK2Service(k2Service); }
		}
		
		public static class ListSubEntitiesForEntity extends EntityInitialValues<K2EntityENT> {
			private K2EntityENT k2Entity;
			public ListSubEntitiesForEntity(K2EntityENT k2Entity) {
				this.k2Entity = k2Entity;
			}
			@Override public void initialize(K2EntityENT entity) { entity.setExtendsEntity(k2Entity); }
		}
		
		public static class ListRootEntitiesForService extends EntityInitialValues<K2EntityENT> {
			private K2ServiceENT k2Service;
			public ListRootEntitiesForService(K2ServiceENT k2Service) {
				this.k2Service = k2Service;
			}
			@Override public void initialize(K2EntityENT entity) { 
				entity.setK2Service(k2Service); 
				entity.setExtendsEntity(null);
			}
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
	public K2Entity newBO(Long id, EntityInitialValues<K2EntityENT> init) { 
		if (id == null) {
			return (K2Entity) serviceContext.putBO(new K2EntityBO(prepareNewEntity(new K2EntityENT(), "Component.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2Entity) serviceContext.putBO(new K2EntityBO(prepareNewEntity(new K2EntityENT(), id, init), PersistenceState.NEW)); 
		}
	}


	
	@Override
	public K2Entity getBO(K2EntityENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2Entity) serviceContext.getBO(entity); }
		return (K2Entity) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2Entity newK2Entity() { return super.newBO(); }
	@Override
	public K2Entity fetchK2Entity(Long id) { return super.fetch(id); }
	@Override
	public K2Entity fetchForName(String name) { return getBO(dao.fetchForName(name)); }
	@Override
	public ServiceList<K2Entity> listForService(K2Service k2Service) { return new Lists.ForService(this, dao, k2Service); }
	@Override
	public ServiceList<K2Entity> listSubEntitiesForEntity(K2Entity k2Entity) { return new Lists.SubEntitiesForEntity(this, dao, k2Entity); }
	@Override
	public ServiceList<K2Entity> listRootEntitiesForService(K2Service k2Service) { return new Lists.RootEntitiesForService(this, dao, k2Service); }

}
