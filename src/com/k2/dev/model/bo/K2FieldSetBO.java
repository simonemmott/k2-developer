package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.FieldSetMember;
import com.k2.dev.model.K2FieldSet;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.ComponentENT.Types;
import com.k2.dev.model.entity.K2FieldSetENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.FieldSetMemberService;
import com.k2.dev.service.K2FieldService;
import com.k2.dev.service.K2FieldSetService;
import com.k2.dev.service.K2ServiceService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2FieldSetBO extends GenericServiceModel implements ServiceModel, K2FieldSet {
	
	@Autowired(required=true)
	protected K2FieldSetService service;
	@Autowired(required=true)
	protected ComponentService componentService;
	@Autowired(required=true)
	protected FieldSetMemberService fieldSetMemberService;
	@Override
	public EntityService getService() { return service; }
	
	public static K2FieldSet NULL = new K2FieldSetBO();
	public K2FieldSetBO() { super(null); }
	public K2FieldSetBO(PersistenceState state) { super(state); }
    public K2FieldSetBO(K2FieldSetENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.FIELD_SET; }
	
	public String getIdentity() { return getName(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2FieldSet Null() { return NULL; }
	
	protected K2FieldSetENT entity;
	@Override
	public K2FieldSetENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }
	
	public Component getComponent() { if (isNull()) { return ComponentBO.NULL; } return Nvl.nvl(componentService.getBO(getEntity().getComponent()), ComponentBO.NULL); }
	@Override
	public void setComponent(Component component) { if (isNull()) { return; } getEntity().setComponent(component.getEntity()); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2FieldSetENT(); }
		if (Component.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((Component)source);
		}
	}
	
	// Lists ----------
	// ServiceComponents list
	@Override
	public ServiceList<Component> getServiceComponents() { return componentService.listForService(getComponent().getK2Service()); }
	@Override
	public ServiceList<FieldSetMember> getMembers() { return fieldSetMemberService.listForFieldSet(this); }

}
