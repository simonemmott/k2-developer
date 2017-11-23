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
import com.k2.dev.dao.K2LinkedFieldDAO;
import com.k2.dev.dao.K2NativeFieldDAO;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2LinkedField;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.bo.EntityFormatterBO;
import com.k2.dev.model.bo.K2FieldBO;
import com.k2.dev.model.bo.K2LinkedFieldBO;
import com.k2.dev.model.bo.K2NativeFieldBO;
import com.k2.dev.model.entity.EntityFormatterENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2LinkedFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2LinkedFieldService;
import com.k2.dev.service.K2NativeFieldService;

@Service("K2LinkedFieldService")
@Transactional
public class K2LinkedFieldServiceImpl extends GenericEntityService<K2LinkedFieldENT, Long, K2LinkedField> implements K2LinkedFieldService{

	public static class Lists {
		
		private static abstract class K2LinkedFieldServiceList extends GenericServiceList<K2LinkedFieldENT, K2LinkedField> implements ServiceList<K2LinkedField> {
			protected K2LinkedFieldDAO dao;
			protected K2LinkedFieldService service;
			public K2LinkedFieldServiceList(K2LinkedFieldService service, K2LinkedFieldDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2LINKEDFIELD; }
		}

		public static class All extends K2LinkedFieldServiceList implements ServiceList<K2LinkedField> {
			public All(K2LinkedFieldService service, K2LinkedFieldDAO dao) { super(service, dao); }
			@Override public K2LinkedField newBO() { return service.newBO(); }
			@Override public K2LinkedField newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2LinkedFieldENT> getList() { return dao.list(); }
			@Override protected K2LinkedField getBO(K2LinkedFieldENT entity) { return service.getBO(entity); }
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


	public K2LinkedFieldServiceImpl() {}

	@Autowired
	private K2LinkedFieldDAO dao;
	@Override
	protected K2LinkedFieldDAO getDAO() { return dao; }
	@Override
	public K2LinkedField nullBO() { return K2LinkedFieldBO.NULL; }
	
	@Override
	public ServiceList<K2LinkedField> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public K2LinkedField newBO(Long id, EntityInitialValues<K2LinkedFieldENT> init) { 
		if (id == null) {
			return (K2LinkedField) serviceContext.putBO(new K2FieldBO(prepareNewEntity(new K2LinkedFieldENT(), "K2Field.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2LinkedField) serviceContext.putBO(new K2FieldBO(prepareNewEntity(new K2LinkedFieldENT(), id, init), PersistenceState.NEW)); 
		}
	}


	
	
	@Override
	public K2LinkedField getBO(K2LinkedFieldENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2LinkedField) serviceContext.getBO(entity); }
		return (K2LinkedField) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2LinkedField newK2LinkedField() { return super.newBO(); }
	@Override
	public K2LinkedField fetchK2LinkedField(Long id) { return super.fetch(id); }


}
