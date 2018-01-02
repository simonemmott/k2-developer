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
import com.k2.dev.dao.ComponentDAO;
import com.k2.dev.dao.K2FieldSetDAO;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2FieldSet;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.ComponentBO;
import com.k2.dev.model.bo.K2FieldSetBO;
import com.k2.dev.model.bo.K2ServiceBO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2FieldSetENT;
import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.K2FieldSetService;

@Service("K2FieldSetService")
@Transactional
public class K2FieldSetServiceImpl extends GenericEntityService<K2FieldSetENT, Long, K2FieldSet> implements K2FieldSetService {

	public static class Lists {
		
		private static abstract class K2FieldSetServiceList extends GenericServiceList<K2FieldSetENT, K2FieldSet> implements ServiceList<K2FieldSet> {
			protected K2FieldSetDAO dao;
			protected K2FieldSetService service;
			public K2FieldSetServiceList(K2FieldSetService service, K2FieldSetDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.FIELD_SET; }
		}

		public static class All extends K2FieldSetServiceList implements ServiceList<K2FieldSet> {
			public All(K2FieldSetService service, K2FieldSetDAO dao) { super(service, dao); }
			@Override public K2FieldSet newBO() { return service.newBO(); }
			@Override public K2FieldSet newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2FieldSetENT> getList() { return dao.list(); }
			@Override protected K2FieldSet getBO(K2FieldSetENT entity) { return service.getBO(entity); }
		}

		public static class ForComponent extends K2FieldSetServiceList implements ServiceList<K2FieldSet> {
			private Component component;
			public ForComponent(K2FieldSetService service, K2FieldSetDAO dao, Component component) { 
				super(service, dao);
				this.component = component;
			}
			@Override public K2FieldSet newBO() { return service.newBO(null, new Init.ListForComponent(component.getEntity())); }
			@Override public K2FieldSet newBO(Long id) { return service.newBO(id, new Init.ListForComponent(component.getEntity())); }
			@Override protected List<K2FieldSetENT> getList() { return dao.listForComponent(component.getEntity()); }
			@Override protected K2FieldSet getBO(K2FieldSetENT entity) { return service.getBO(entity); }
		}

}
	public static class Init {
		
		public static class ListForComponent extends EntityInitialValues<K2FieldSetENT> {
			private ComponentENT component;
			public ListForComponent(ComponentENT component) {
				this.component = component;
			}
			@Override public void initialize(K2FieldSetENT entity) { entity.setComponent(component); }
		}
		
	}


	public K2FieldSetServiceImpl() {}
	
	@Autowired
	K2FieldSetDAO dao;
	@Override
	protected K2FieldSetDAO getDAO() { return dao; }

	
	@Override
	public K2FieldSet nullBO() { return K2FieldSetBO.NULL; }
	@Override
	public K2FieldSet getBO(K2FieldSetENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2FieldSet) serviceContext.getBO(entity); }
		return (K2FieldSet) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2FieldSet newBO(Long id, EntityInitialValues<K2FieldSetENT> init) { 
		if (id == null) {
			return (K2FieldSet) serviceContext.putBO(new K2FieldSetBO(prepareNewEntity(new K2FieldSetENT(), "K2FieldSet.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2FieldSet) serviceContext.putBO(new K2FieldSetBO(prepareNewEntity(new K2FieldSetENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public K2FieldSet newFieldSet() { return super.newBO(); }
	@Override
	public K2FieldSet fetchFieldSet(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<K2FieldSet> listAll() { return new Lists.All(this, dao); }


	@Override
	public ServiceList<K2FieldSet> listForComponent(Component component) { return new Lists.ForComponent(this, dao, component); }


}
