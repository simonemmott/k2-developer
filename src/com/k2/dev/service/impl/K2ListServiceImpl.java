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
import com.k2.dev.dao.K2ListDAO;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2List;
import com.k2.dev.model.bo.K2ListBO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ListENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2ListService;

@Service("K2ListService")
@Transactional
public class K2ListServiceImpl extends GenericEntityService<K2ListENT, Long, K2List> implements K2ListService {

	public static class Lists {
		
		private static abstract class K2ListServiceList extends GenericServiceList<K2ListENT, K2List> implements ServiceList<K2List> {
			protected K2ListDAO dao;
			protected K2ListService service;
			public K2ListServiceList(K2ListService service, K2ListDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2LIST; }
		}

		public static class All extends K2ListServiceList implements ServiceList<K2List> {
			public All(K2ListService service, K2ListDAO dao) { super(service, dao); }
			@Override public K2List newBO() { return service.newBO(); }
			@Override public K2List newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2ListENT> getList() { return dao.list(); }
			@Override protected K2List getBO(K2ListENT entity) { return service.getBO(entity); }
		}
		public static class ForEntity extends K2ListServiceList implements ServiceList<K2List> {
			private K2Entity entity;
			public ForEntity(K2ListService service, K2ListDAO dao, K2Entity entity) { 
				super(service, dao);
				this.entity = entity;
			}
			@Override public K2List newBO() { return service.newBO(null, new Init.ListForEntity(entity.getEntity())); }
			@Override public K2List newBO(Long id) { return service.newBO(id, new Init.ListForEntity(entity.getEntity())); }
			@Override protected List<K2ListENT> getList() { return dao.listForEntity(entity.getEntity()); }
			@Override protected K2List getBO(K2ListENT entity) { return service.getBO(entity); }
		}


	}
	public static class Init {
				
		public static class ListForEntity extends EntityInitialValues<K2ListENT> {
			private K2EntityENT entity;
			public ListForEntity(K2EntityENT entity) {
				this.entity = entity;
			}
			@Override public void initialize(K2ListENT list) { list.setK2Entity(entity); }
		}
		
	}


	public K2ListServiceImpl() {}
	
	@Autowired
	K2ListDAO dao;
	@Override
	protected K2ListDAO getDAO() { return dao; }

	
	@Override
	public K2List nullBO() { return K2ListBO.NULL; }
	@Override
	public K2List getBO(K2ListENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2List) serviceContext.getBO(entity); }
		return (K2List) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2List newBO(Long id, EntityInitialValues<K2ListENT> init) { 
		if (id == null) {
			return (K2List) serviceContext.putBO(new K2ListBO(prepareNewEntity(new K2ListENT(), "K2List.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2List) serviceContext.putBO(new K2ListBO(prepareNewEntity(new K2ListENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public K2List newK2List() { return super.newBO(); }
	@Override
	public K2List fetchK2List(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<K2List> listAll() { return new Lists.All(this, dao); }

	@Override
	public ServiceList<K2List> listForEnity(K2Entity k2Entity) { return new Lists.ForEntity(this, dao, k2Entity); }


}
