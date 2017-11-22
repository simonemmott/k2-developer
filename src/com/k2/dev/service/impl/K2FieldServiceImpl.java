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
import com.k2.dev.dao.K2FieldDAO;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.bo.EntityFormatterBO;
import com.k2.dev.model.bo.K2FieldBO;
import com.k2.dev.model.entity.EntityFormatterENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2FieldService;

@Service("K2FieldService")
@Transactional
public class K2FieldServiceImpl extends GenericEntityService<K2FieldENT, Long, K2Field> implements K2FieldService{

	public static class Lists {
		
		private static abstract class K2FieldServiceList extends GenericServiceList<K2FieldENT, K2Field> implements ServiceList<K2Field> {
			protected K2FieldDAO dao;
			protected K2FieldService service;
			public K2FieldServiceList(K2FieldService service, K2FieldDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2FIELD; }
		}

		public static class All extends K2FieldServiceList implements ServiceList<K2Field> {
			public All(K2FieldService service, K2FieldDAO dao) { super(service, dao); }
			@Override public K2Field newBO() { return service.newBO(); }
			@Override public K2Field newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2FieldENT> getList() { return dao.list(); }
			@Override protected K2Field getBO(K2FieldENT entity) { return service.getBO(entity); }
		}

		public static class ForEntity extends K2FieldServiceList implements ServiceList<K2Field> {
			private K2Entity entity;
			public ForEntity(K2FieldService service, K2FieldDAO dao, K2Entity entity) { 
				super(service, dao);
				this.entity = entity;
			}
			@Override public K2Field newBO() { return service.newBO(null, new Init.ListForEntity(entity.getEntity())); }
			@Override public K2Field newBO(Long id) { return service.newBO(id, new Init.ListForEntity(entity.getEntity())); }
			@Override protected List<K2FieldENT> getList() { return dao.listForEntity(entity.getEntity()); }
			@Override protected K2Field getBO(K2FieldENT entity) { return service.getBO(entity); }
		}

	}
	
	public static class Init {
		
		public static class ListForEntity extends EntityInitialValues<K2FieldENT> {
			private K2EntityENT entity;
			public ListForEntity(K2EntityENT entity) {
				this.entity = entity;
			}
			@Override public void initialize(K2FieldENT field) { field.setK2Entity(entity); }
		}
		
	}


	public K2FieldServiceImpl() {}

	@Autowired
	private K2FieldDAO dao;
	@Override
	protected K2FieldDAO getDAO() { return dao; }
	@Override
	public K2Field nullBO() { return K2FieldBO.NULL; }
	
	@Override
	public ServiceList<K2Field> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public K2Field newBO(Long id, EntityInitialValues<K2FieldENT> init) { 
		if (id == null) {
			return (K2Field) serviceContext.putBO(new K2FieldBO(prepareNewEntity(new K2FieldENT(), "K2Field.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2Field) serviceContext.putBO(new K2FieldBO(prepareNewEntity(new K2FieldENT(), id, init), PersistenceState.NEW)); 
		}
	}


	
	
	@Override
	public K2Field getBO(K2FieldENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2Field) serviceContext.getBO(entity); }
		return (K2Field) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2Field newK2Field() { return super.newBO(); }
	@Override
	public K2Field fetchK2Field(Long id) { return super.fetch(id); }
	@Override
	public ServiceList<K2Field> listForEntity(K2Entity k2Entity) { return new Lists.ForEntity(this, dao, k2Entity); }


}
