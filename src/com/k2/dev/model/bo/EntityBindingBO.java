package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.EntityBinding;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.entity.EntityBindingENT;
import com.k2.dev.service.EntityBindingService;
import com.k2.dev.service.K2FieldService;

@SuppressWarnings("rawtypes")
@Configurable
public class EntityBindingBO extends GenericServiceModel implements ServiceModel, EntityBinding {
	
	@Autowired(required=true)
	protected EntityBindingService service;
	@Override
	public EntityService  getService() { return service; }
	
	@Autowired
	K2FieldService fieldService;
	
	public static EntityBinding NULL = new EntityBindingBO();
	public EntityBindingBO() { super(null); }
	public EntityBindingBO(PersistenceState state) { super(state); }
	public EntityBindingBO(EntityBindingENT entity, PersistenceState state) { super(state); this.entity = entity; }
	
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public EntityBinding Null() { return NULL; }
	
	private EntityBindingENT entity;
	@Override
	public EntityBindingENT getEntity() { return entity; }

	@Override
	public K2Field getBindingField() { if (isNull()) { return K2FieldBO.NULL; } return fieldService.getBO(entity.getBindingField()); }
	@Override
	public void setBindingField(K2Field bindingField) { if (isNull()) { return; } entity.setBindingField(bindingField.getEntity()); changed(); }
	
}