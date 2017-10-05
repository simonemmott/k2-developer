package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.EntityFormatterDAO;
import com.k2.dev.model.EntityBinding;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.bo.EntityBindingBO;
import com.k2.dev.model.bo.EntityFormatterBO;
import com.k2.dev.model.entity.EntityBindingENT;
import com.k2.dev.model.entity.EntityFormatterENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.EntityFormatterService;

@Service("entityFormatterService")
@Transactional
public class EntityFormatterServiceImpl extends GenericEntityService<EntityFormatterENT, Long, EntityFormatter> implements EntityFormatterService{

	public static class Lists {
		
		private static abstract class EntityFormatterServiceList extends GenericServiceList<EntityFormatterENT, EntityFormatter> implements ServiceList<EntityFormatter> {
			protected EntityFormatterDAO dao;
			protected EntityFormatterService service;
			public EntityFormatterServiceList(EntityFormatterService service, EntityFormatterDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaEntity getMetaEntity() { return MetaModel.Entities.ENTITY_FORMATTER; }
		}

		public static class All extends EntityFormatterServiceList implements ServiceList<EntityFormatter> {
			public All(EntityFormatterService service, EntityFormatterDAO dao) { super(service, dao); }
			@Override public EntityFormatter newBO() { return service.newBO(); }
			@Override public EntityFormatter newBO(Long id) { return service.newBO(id); }
			@Override protected List<EntityFormatterENT> getList() { return dao.list(); }
			@Override protected EntityFormatter getBO(EntityFormatterENT entity) { return service.getBO(entity); }
		}

	}

	public EntityFormatterServiceImpl() {}

	@Autowired
	private EntityFormatterDAO dao;
	@Override
	protected EntityFormatterDAO getDAO() { return dao; }
	@Override
	public EntityFormatter nullBO() { return EntityFormatterBO.NULL; }
	
	@Override
	public ServiceList<EntityFormatter> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public EntityFormatter newBO(Long id, EntityInitialValues<EntityFormatterENT> init) { 
		if (id == null) {
			return (EntityFormatter) serviceContext.putBO(new EntityFormatterBO(prepareNewEntity(new EntityFormatterENT(), "EntityBinding.ID", init), PersistenceState.NEW)); 
		} else {
			return (EntityFormatter) serviceContext.putBO(new EntityFormatterBO(prepareNewEntity(new EntityFormatterENT(), id, init), PersistenceState.NEW)); 
		}
	}


	
	
	@Override
	public EntityFormatter getBO(EntityFormatterENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (EntityFormatter) serviceContext.getBO(entity); }
		return (EntityFormatter) serviceContext.putBO(new EntityFormatterBO(entity, PersistenceState.PERSISTED));
	}
	@Override
	public EntityFormatter newEntityFormatter() { return super.newBO(); }
	@Override
	public EntityFormatter fetchEntityFormatter(Long id) { return super.fetch(id); }


}
