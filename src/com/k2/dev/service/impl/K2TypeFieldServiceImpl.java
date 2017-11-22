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
import com.k2.dev.dao.K2TypeFieldDAO;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2LinkedField;
import com.k2.dev.model.K2NativeField;
import com.k2.dev.model.K2TypeField;
import com.k2.dev.model.bo.EntityFormatterBO;
import com.k2.dev.model.bo.K2FieldBO;
import com.k2.dev.model.bo.K2LinkedFieldBO;
import com.k2.dev.model.bo.K2NativeFieldBO;
import com.k2.dev.model.bo.K2TypeFieldBO;
import com.k2.dev.model.entity.EntityFormatterENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2LinkedFieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;
import com.k2.dev.model.entity.K2TypeFieldENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2LinkedFieldService;
import com.k2.dev.service.K2NativeFieldService;
import com.k2.dev.service.K2TypeFieldService;

@Service("K2TypeFieldService")
@Transactional
public class K2TypeFieldServiceImpl extends GenericEntityService<K2TypeFieldENT, Long, K2TypeField> implements K2TypeFieldService{

	public static class Lists {
		
		private static abstract class K2TypeFieldServiceList extends GenericServiceList<K2TypeFieldENT, K2TypeField> implements ServiceList<K2TypeField> {
			protected K2TypeFieldDAO dao;
			protected K2TypeFieldService service;
			public K2TypeFieldServiceList(K2TypeFieldService service, K2TypeFieldDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2TYPEFIELD; }
		}

		public static class All extends K2TypeFieldServiceList implements ServiceList<K2TypeField> {
			public All(K2TypeFieldService service, K2TypeFieldDAO dao) { super(service, dao); }
			@Override public K2TypeField newBO() { return service.newBO(); }
			@Override public K2TypeField newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2TypeFieldENT> getList() { return dao.list(); }
			@Override protected K2TypeField getBO(K2TypeFieldENT entity) { return service.getBO(entity); }
		}

	}
	
	public static class Init {
		
		
	}


	public K2TypeFieldServiceImpl() {}

	@Autowired
	private K2TypeFieldDAO dao;
	@Override
	protected K2TypeFieldDAO getDAO() { return dao; }
	@Override
	public K2TypeField nullBO() { return K2TypeFieldBO.NULL; }
	
	@Override
	public ServiceList<K2TypeField> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public K2TypeField newBO(Long id, EntityInitialValues<K2TypeFieldENT> init) { 
		if (id == null) {
			return (K2TypeField) serviceContext.putBO(new K2FieldBO(prepareNewEntity(new K2TypeFieldENT(), "K2Field.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2TypeField) serviceContext.putBO(new K2FieldBO(prepareNewEntity(new K2TypeFieldENT(), id, init), PersistenceState.NEW)); 
		}
	}


	
	
	@Override
	public K2TypeField getBO(K2TypeFieldENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2TypeField) serviceContext.getBO(entity); }
		return (K2TypeField) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2TypeField newK2TypeField() { return super.newBO(); }
	@Override
	public K2TypeField fetchK2TypeField(Long id) { return super.fetch(id); }


}
