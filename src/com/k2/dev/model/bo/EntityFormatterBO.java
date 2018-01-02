package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.EntityBinding;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.entity.EntityBindingENT;
import com.k2.dev.model.entity.EntityFormatterENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.EntityFormatterService;
import com.k2.dev.service.K2EntityService;

@SuppressWarnings("rawtypes")
@Configurable
public class EntityFormatterBO extends GenericServiceModel implements ServiceModel, EntityFormatter {
	
	@Autowired(required=true)
	protected EntityFormatterService service;
	@Override
	public EntityService  getService() { return service; }
	
	@Autowired
	K2EntityService entityService;
	
	public static EntityFormatter NULL = new EntityFormatterBO();
	public EntityFormatterBO() { super(null); }
	public EntityFormatterBO(PersistenceState state) { super(state); }
	public EntityFormatterBO(EntityFormatterENT entity, PersistenceState state) { super(state); this.entity = entity; }
	
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.ENTITYFORMATTER; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public EntityFormatter Null() { return NULL; }
	
	protected EntityFormatterENT entity;
	@Override 
	public EntityFormatterENT getEntity() { return entity; }

	@Override
	public  K2Entity getEntityToFormat() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(entityService.getBO(entity.getEntityToFormat()), K2EntityBO.NULL); }
	@Override
	public void setEntityToFormat(K2Entity aEntity) { if (isNull()) { return; } entity.setEntityToFormat(aEntity.getEntity()); changed(); }
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new EntityFormatterENT(); }
		if (EntityFormatter.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((EntityFormatter)source);
		}
	}


}
