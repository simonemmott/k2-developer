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
import com.k2.dev.dao.K2NativeFieldDAO;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.bo.EntityFormatterBO;
import com.k2.dev.model.bo.K2FieldBO;
import com.k2.dev.model.bo.K2NativeFieldBO;
import com.k2.dev.model.entity.EntityFormatterENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2NativeFieldService;

@Service("K2NativeFieldService")
@Transactional
public class K2NativeFieldServiceImpl extends GenericEntityService<K2NativeFieldENT, Long, K2NativeField> implements K2NativeFieldService{

	public static class Lists {
		
		private static abstract class K2NativeFieldServiceList extends GenericServiceList<K2NativeFieldENT, K2NativeField> implements ServiceList<K2NativeField> {
			protected K2NativeFieldDAO dao;
			protected K2NativeFieldService service;
			public K2NativeFieldServiceList(K2NativeFieldService service, K2NativeFieldDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.NATIVE_FIELD; }
		}

		public static class All extends K2NativeFieldServiceList implements ServiceList<K2NativeField> {
			public All(K2NativeFieldService service, K2NativeFieldDAO dao) { super(service, dao); }
			@Override public K2NativeField newBO() { return service.newBO(); }
			@Override public K2NativeField newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2NativeFieldENT> getList() { return dao.list(); }
			@Override protected K2NativeField getBO(K2NativeFieldENT entity) { return service.getBO(entity); }
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


	public K2NativeFieldServiceImpl() {}

	@Autowired
	private K2NativeFieldDAO dao;
	@Override
	protected K2NativeFieldDAO getDAO() { return dao; }
	@Override
	public K2NativeField nullBO() { return K2NativeFieldBO.NULL; }
	
	@Override
	public ServiceList<K2NativeField> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public K2NativeField newBO(Long id, EntityInitialValues<K2NativeFieldENT> init) { 
		if (id == null) {
			return (K2NativeField) serviceContext.putBO(new K2FieldBO(prepareNewEntity(new K2NativeFieldENT(), "K2Field.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2NativeField) serviceContext.putBO(new K2FieldBO(prepareNewEntity(new K2NativeFieldENT(), id, init), PersistenceState.NEW)); 
		}
	}


	
	
	@Override
	public K2NativeField getBO(K2NativeFieldENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2NativeField) serviceContext.getBO(entity); }
		return (K2NativeField) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2NativeField newK2NativeField() { return super.newBO(); }
	@Override
	public K2NativeField fetchK2NativeField(Long id) { return super.fetch(id); }


}
