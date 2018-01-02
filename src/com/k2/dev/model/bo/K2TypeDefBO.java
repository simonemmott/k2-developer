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
import com.k2.dev.model.entity.ComponentENT.Types.ComponentType;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2TypeDefService;
import com.k2.dev.service.K2TypeValueService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2TypeDefBO extends GenericServiceModel implements ServiceModel, K2TypeDef {
	
	@Autowired(required=true)
	protected K2TypeDefService service;
	@Autowired(required=true)
	protected K2EntityService k2EntityService;
	@Autowired(required=true)
	protected K2TypeValueService k2TypeValueService;
	@Override
	public EntityService getService() { return service; }
	
	public static K2TypeDef NULL = new K2TypeDefBO();
	public K2TypeDefBO() { super(null); }
	public K2TypeDefBO(PersistenceState state) { super(state); }
    public K2TypeDefBO(K2TypeDefENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.TYPE_DEFINITION; }
	
	public String getIdentity() { return getName(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2TypeDef Null() { return NULL; }
	
	protected K2TypeDefENT entity;
	@Override
	public K2TypeDefENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return getEntity().getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } getEntity().setName(name); changed(); }
	
	@Override
	public String getAlias() { if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } getEntity().setAlias(alias); changed(); }
	
	@Override
	public K2Entity getK2Entity() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(k2EntityService.getBO(getEntity().getK2Entity()), K2EntityBO.NULL); }
	@Override
	public void setK2Entity(K2Entity k2Entity) { if (isNull()) { return; } getEntity().setK2Entity(k2Entity.getEntity()); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2TypeDefENT(); }
		if (K2TypeDef.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2TypeDef)source);
		}
	}

	@Override
	public ServiceList<K2Entity> getEntities() { return k2EntityService.listAll(); }
	@Override
	public ServiceList<K2TypeValue> getTypeValues() { return k2TypeValueService.listForType(this); }


}
