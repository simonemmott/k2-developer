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
import com.k2.dev.dao.K2TypeValueDAO;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.bo.K2TypeValueBO;
import com.k2.dev.model.entity.K2TypeDefENT;
import com.k2.dev.model.entity.K2TypeValueENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2TypeValueService;

@Service("K2TypeValueService")
@Transactional
public class K2TypeValueServiceImpl extends GenericEntityService<K2TypeValueENT, Long, K2TypeValue> implements K2TypeValueService {

	public static class Lists {
		
		private static abstract class K2TypeValueServiceList extends GenericServiceList<K2TypeValueENT, K2TypeValue> implements ServiceList<K2TypeValue> {
			protected K2TypeValueDAO dao;
			protected K2TypeValueService service;
			public K2TypeValueServiceList(K2TypeValueService service, K2TypeValueDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2TYPEVALUE; }
		}

		public static class All extends K2TypeValueServiceList implements ServiceList<K2TypeValue> {
			public All(K2TypeValueService service, K2TypeValueDAO dao) { super(service, dao); }
			@Override public K2TypeValue newBO() { return service.newBO(); }
			@Override public K2TypeValue newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2TypeValueENT> getList() { return dao.list(); }
			@Override protected K2TypeValue getBO(K2TypeValueENT entity) { return service.getBO(entity); }
		}
		
		public static class ForType extends K2TypeValueServiceList implements ServiceList<K2TypeValue> {
			private K2TypeDef typeDefinition;
			public ForType(K2TypeValueService service, K2TypeValueDAO dao, K2TypeDef typeDefinition) { 
				super(service, dao);
				this.typeDefinition = typeDefinition;
			}
			@Override public K2TypeValue newBO() { return service.newBO(null, new Init.ListForType(typeDefinition.getEntity())); }
			@Override public K2TypeValue newBO(Long id) { return service.newBO(id, new Init.ListForType(typeDefinition.getEntity())); }
			@Override protected List<K2TypeValueENT> getList() { return dao.ListForType(typeDefinition.getEntity()); }
			@Override protected K2TypeValue getBO(K2TypeValueENT entity) { return service.getBO(entity); }
		}

	}
	public static class Init {
				
		public static class ListForType extends EntityInitialValues<K2TypeValueENT> {
			private K2TypeDefENT entity;
			public ListForType(K2TypeDefENT entity) {
				this.entity = entity;
			}
			@Override public void initialize(K2TypeValueENT value) { value.setTypeDefinition(entity);; }
		}
		
	}


	public K2TypeValueServiceImpl() {}
	
	@Autowired
	K2TypeValueDAO dao;
	@Override
	protected K2TypeValueDAO getDAO() { return dao; }

	
	@Override
	public K2TypeValue nullBO() { return K2TypeValueBO.NULL; }
	@Override
	public K2TypeValue getBO(K2TypeValueENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2TypeValue) serviceContext.getBO(entity); }
		return (K2TypeValue) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2TypeValue newBO(Long id, EntityInitialValues<K2TypeValueENT> init) { 
		if (id == null) {
			return (K2TypeValue) serviceContext.putBO(new K2TypeValueBO(prepareNewEntity(new K2TypeValueENT(), "K2TypeValue.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2TypeValue) serviceContext.putBO(new K2TypeValueBO(prepareNewEntity(new K2TypeValueENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public K2TypeValue newK2TypeValue() { return super.newBO(); }
	@Override
	public K2TypeValue fetchK2TypeValue(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<K2TypeValue> listAll() { return new Lists.All(this, dao); }


	@Override
	public ServiceList<K2TypeValue> listForType(K2TypeDef typeDefinition) { return new Lists.ForType(this, dao, typeDefinition);
	}


}
