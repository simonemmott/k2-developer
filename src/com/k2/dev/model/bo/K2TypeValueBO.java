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
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2TypeDef;
import com.k2.dev.model.K2TypeValue;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2TypeDefENT;
import com.k2.dev.model.entity.K2TypeValueENT;
import com.k2.dev.model.entity.ComponentENT.Types.ComponentType;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2TypeDefService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2TypeValueBO extends GenericServiceModel implements ServiceModel, K2TypeValue {
	
	@Autowired(required=true)
	protected K2TypeDefService service;
	@Autowired(required=true)
	protected K2TypeDefService k2TypeDefService;
	@Override
	public EntityService getService() { return service; }
	
	public static K2TypeValue NULL = new K2TypeValueBO();
	public K2TypeValueBO() { super(null); }
	public K2TypeValueBO(PersistenceState state) { super(state); }
    public K2TypeValueBO(K2TypeValueENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2TYPEVALUE; }
	
	public String getIdentity() { return getName(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2TypeValue Null() { return NULL; }
	
	protected K2TypeValueENT entity;
	@Override
	public K2TypeValueENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getAlias() { if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } getEntity().setAlias(alias); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }
	
	@Override
	public String getDescription() { if (isNull()) { return null; } return getEntity().getDescription(); }
	@Override
	public void setDescription(String description) { if (isNull()) { return; } getEntity().setDescription(description); changed(); }
	
	@Override
	public K2TypeDef getTypeDefinition() { if (isNull()) { return K2TypeDefBO.NULL; } return Nvl.nvl(k2TypeDefService.getBO(getEntity().getTypeDefinition()), K2TypeDefBO.NULL); }
	@Override
	public void setTypeDefinition(K2TypeDef k2TypeDefinition) { if (isNull()) { return; } getEntity().setTypeDefinition(k2TypeDefinition.getEntity()); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2TypeValueENT(); }
		if (K2TypeValue.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2TypeValue)source);
		}
	}

	@Override
	public ServiceList<K2TypeDef> getTypeDefinitions() { return k2TypeDefService.listAll(); }


}
