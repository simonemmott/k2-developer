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
import com.k2.dev.model.K2List;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ListENT;
import com.k2.dev.model.entity.ComponentENT.Types.ComponentType;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2ListService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2ListBO extends GenericServiceModel implements ServiceModel, K2List {
	
	@Autowired(required=true)
	protected K2ListService service;
	@Autowired(required=true)
	protected K2EntityService k2EntityService;
	@Override
	public EntityService getService() { return service; }
	
	public static K2List NULL = new K2ListBO();
	public K2ListBO() { super(null); }
	public K2ListBO(PersistenceState state) { super(state); }
    public K2ListBO(K2ListENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2LIST; }
	
	public String getIdentity() { return getName(); }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2List Null() { return NULL; }
	
	protected K2ListENT entity;
	@Override
	public K2ListENT getEntity() { return entity; }
	
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
	
	// ListOfEntity field
	@Override
    public K2Entity getListOfEntity() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(k2EntityService.getBO(getEntity().getListOfEntity()), K2EntityBO.NULL); }
	@Override
    public void setListOfEntity(K2Entity listOfEntity) { if (isNull()) { return; } getEntity().setListOfEntity(listOfEntity.getEntity()); changed(); }

	// K2Entity field
	@Override
    public K2Entity getK2Entity() { if (isNull()) { return K2EntityBO.NULL; } return Nvl.nvl(k2EntityService.getBO(getEntity().getK2Entity()), K2EntityBO.NULL); }
	@Override
	public void setK2Entity(K2Entity k2Entity) { if (isNull()) { return; } getEntity().setK2Entity(k2Entity.getEntity()); changed(); }
    	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2ListENT(); }
		if (K2List.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2List)source);
		}
	}
	@Override
	public ServiceList<K2Entity> getEntities() { return k2EntityService.listAll(); }

}
