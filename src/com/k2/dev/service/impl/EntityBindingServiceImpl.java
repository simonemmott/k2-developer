package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.EntityBindingDAO;
import com.k2.dev.model.EntityBinding;
import com.k2.dev.model.bo.EntityBindingBO;
import com.k2.dev.model.entity.EntityBindingENT;
import com.k2.dev.service.EntityBindingService;

@Service("entityBindingService")
@Transactional
public class EntityBindingServiceImpl extends GenericEntityService<EntityBindingENT, Long, EntityBinding> implements EntityBindingService{

	public static class Lists {
		
		private static abstract class EntityBindingServiceList extends GenericServiceList<EntityBindingENT, EntityBinding> implements ServiceList<EntityBinding> {
			protected EntityBindingDAO dao;
			protected EntityBindingService service;
			public EntityBindingServiceList(EntityBindingService service, EntityBindingDAO dao) { 
				this.service = service; 
				this.dao = dao; 
			}
		}

		public static class All extends EntityBindingServiceList implements ServiceList<EntityBinding> {
			public All(EntityBindingService service, EntityBindingDAO dao) { super(service, dao); }
			@Override public EntityBinding newBO() { return service.newBO(); }
			@Override protected List<EntityBindingENT> getList() { return dao.list(); }
			@Override protected EntityBinding getBO(EntityBindingENT entity) { return service.getBO(entity); }
		}

	}

	public EntityBindingServiceImpl() {}

	@Autowired
	private EntityBindingDAO dao;
	@Override
	protected EntityBindingDAO getDAO() { return dao; }
	@Override
	protected EntityBinding nullBO() { return EntityBindingBO.NULL; }
	
	@Override
	public ServiceList<EntityBinding> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public EntityBinding newBO(EntityInitialValues<EntityBindingENT> init) {
		return (EntityBinding) serviceContext.putBO(new EntityBindingBO(prepareNewEntity(new EntityBindingENT(), "EntityBinding.ID", init), PersistenceState.NEW)); 
	}
	@Override
	public EntityBinding getBO(EntityBindingENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (EntityBinding) serviceContext.getBO(entity); }
		return (EntityBinding) serviceContext.putBO(new EntityBindingBO(entity, PersistenceState.PERSISTED));
	}
	@Override
	public EntityBinding newEntityBinding() { return super.newBO(); }
	@Override
	public EntityBinding fetchEntityBinding(Long id) { return super.fetch(id); }


}
