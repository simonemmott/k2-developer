package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.Dependency;
import com.k2.dev.model.EntityBinding;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.entity.DependencyENT;
import com.k2.dev.model.entity.EntityBindingENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.EntityBindingService;
import com.k2.dev.service.K2FieldService;

@SuppressWarnings("rawtypes")
@Configurable
public class EntityBindingBO extends K2SnippetBindingBO implements ServiceModel, EntityBinding {
	
	@Autowired(required=true)
	protected EntityBindingService service;
	@Override
	public EntityBindingService  getService() { return service; }
	
	@Autowired
	K2FieldService fieldService;
	
	public static EntityBinding NULL = new EntityBindingBO();
	public EntityBindingBO() { super(null); }
	public EntityBindingBO(PersistenceState state) { super(state); }
	public EntityBindingBO(EntityBindingENT entity, PersistenceState state) { super(state); this.entity = entity; }
	
	
	
	
		

	
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.ENTITY_BINDING; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public EntityBinding Null() { return NULL; }
	
	protected EntityBindingENT entity;
	@Override
	public EntityBindingENT getEntity() { return entity; }

	@Override
	public K2Field getBindingField() { if (isNull()) { return K2FieldBO.NULL; } return Nvl.nvl(fieldService.getBO(getEntity().getBindingField()), K2FieldBO.NULL); }
	@Override
	public void setBindingField(K2Field bindingField) { if (isNull()) { return; } getEntity().setBindingField(bindingField.getEntity()); changed(); }
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new EntityBindingENT(); }
		if (EntityBinding.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((EntityBinding)source);
		}
	}


}
