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
import com.k2.dev.dao.K2TypeDefDAO;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.bo.K2TypeDefBO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2TypeDefENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2TypeDefService;

@Service("K2TypeDefService")
@Transactional
public class K2TypeDefServiceImpl extends GenericEntityService<K2TypeDefENT, Long, K2TypeDef> implements K2TypeDefService {

	public static class Lists {
		
		private static abstract class K2TypeDefServiceList extends GenericServiceList<K2TypeDefENT, K2TypeDef> implements ServiceList<K2TypeDef> {
			protected K2TypeDefDAO dao;
			protected K2TypeDefService service;
			public K2TypeDefServiceList(K2TypeDefService service, K2TypeDefDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2TYPEDEF; }
		}

		public static class All extends K2TypeDefServiceList implements ServiceList<K2TypeDef> {
			public All(K2TypeDefService service, K2TypeDefDAO dao) { super(service, dao); }
			@Override public K2TypeDef newBO() { return service.newBO(); }
			@Override public K2TypeDef newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2TypeDefENT> getList() { return dao.list(); }
			@Override protected K2TypeDef getBO(K2TypeDefENT entity) { return service.getBO(entity); }
		}

		public static class ForEntity extends K2TypeDefServiceList implements ServiceList<K2TypeDef> {
			private K2Entity entity;
			public ForEntity(K2TypeDefService service, K2TypeDefDAO dao, K2Entity entity) { 
				super(service, dao);
				this.entity = entity;
			}
			@Override public K2TypeDef newBO() { return service.newBO(null, new Init.ListForEntity(entity.getEntity())); }
			@Override public K2TypeDef newBO(Long id) { return service.newBO(id, new Init.ListForEntity(entity.getEntity())); }
			@Override protected List<K2TypeDefENT> getList() { return dao.listForEntity(entity.getEntity()); }
			@Override protected K2TypeDef getBO(K2TypeDefENT entity) { return service.getBO(entity); }
		}

}
	public static class Init {
				
		public static class ListForEntity extends EntityInitialValues<K2TypeDefENT> {
			private K2EntityENT entity;
			public ListForEntity(K2EntityENT entity) {
				this.entity = entity;
			}
			@Override public void initialize(K2TypeDefENT field) { field.setK2Entity(entity); }
		}
		
	}


	public K2TypeDefServiceImpl() {}
	
	@Autowired
	K2TypeDefDAO dao;
	@Override
	protected K2TypeDefDAO getDAO() { return dao; }

	
	@Override
	public K2TypeDef nullBO() { return K2TypeDefBO.NULL; }
	@Override
	public K2TypeDef getBO(K2TypeDefENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2TypeDef) serviceContext.getBO(entity); }
		return (K2TypeDef) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2TypeDef newBO(Long id, EntityInitialValues<K2TypeDefENT> init) { 
		if (id == null) {
			return (K2TypeDef) serviceContext.putBO(new K2TypeDefBO(prepareNewEntity(new K2TypeDefENT(), "K2Type.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2TypeDef) serviceContext.putBO(new K2TypeDefBO(prepareNewEntity(new K2TypeDefENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public K2TypeDef newK2TypeDef() { return super.newBO(); }
	@Override
	public K2TypeDef fetchK2TypeDef(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<K2TypeDef> listAll() { return new Lists.All(this, dao); }


	@Override
	public ServiceList<K2TypeDef> listForEntity(K2Entity k2Entity) { return new Lists.ForEntity(this, dao, k2Entity);
	}


}
