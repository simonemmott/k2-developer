package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.ComponentENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.K2ServiceService;

@SuppressWarnings("rawtypes")
@Configurable
public class ComponentBO extends GenericServiceModel implements ServiceModel, Component {
	
	@Autowired(required=true)
	protected ComponentService service;
	@Autowired(required=true)
	protected K2ServiceService k2ServiceService;
	@Override
	public EntityService getService() { return service; }
	
	public static Component NULL = new ComponentBO();
	public ComponentBO() { super(null); }
	public ComponentBO(PersistenceState state) { super(state); }
    public ComponentBO(ComponentENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.COMPONENT; }
	
	public String getIdentity() { return getName(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Component Null() { return NULL; }
	
	protected ComponentENT entity;
	@Override
	public ComponentENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }
	
	@Override
	public String getPackageName() { if (isNull()) { return null; } return getEntity().getPackageName(); }
	@Override
	public void setPackageName(String packageName) { if (isNull()) { return; } getEntity().setPackageName(packageName); changed(); }
	
	@Override
	public Types.ComponentType getComponentType() { if (isNull()) { return null; } return getEntity().getComponentType(); }

	
	// DiscriminatorType field
	@Override
	public K2Service getK2Service() { if (isNull()) { return K2ServiceBO.NULL; } return Nvl.nvl(k2ServiceService.getBO(getEntity().getK2Service()), K2ServiceBO.NULL); }
	@Override
	public void setK2Service(K2Service k2Service) { if (isNull()) { return; } getEntity().setK2Service(k2Service.getEntity()); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new ComponentENT(); }
		if (Component.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((Component)source);
		}
	}

}
