package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.Component;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;

@SuppressWarnings("rawtypes")
@Configurable
public class ComponentBO extends GenericServiceModel implements ServiceModel, Component {
	
	@Autowired(required=true)
	protected ComponentService service;
	@Override
	public EntityService getService() { return service; }
	
	public static Component NULL = new ComponentBO();
	public ComponentBO() { super(null); }
	public ComponentBO(PersistenceState state) { super(state); }
    public ComponentBO(ComponentENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaEntity getMetaEntity() { return MetaModel.Entities.COMPONENT; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Component Null() { return NULL; }
	
	private ComponentENT entity;
	@Override
	public ComponentENT getEntity() { return entity; }
	
	@Override
	public Long getID() { if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return entity.getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } entity.setName(name); changed(); }
	
	@Override
	public String getPackageName() { if (isNull()) { return null; } return entity.getPackageName(); }
	@Override
	public void setPackageName(String packageName) { if (isNull()) { return; } entity.setPackageName(packageName); changed(); }
	
}
