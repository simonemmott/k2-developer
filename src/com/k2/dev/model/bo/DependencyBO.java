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
import com.k2.dev.model.Dependency;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Method;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.DependencyENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.DependencyService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2MethodService;

@SuppressWarnings("rawtypes")
@Configurable
public class DependencyBO extends GenericServiceModel implements ServiceModel, Dependency {
	
	@Autowired(required=true)
	protected DependencyService service;
	@Autowired(required=true)
	protected K2EntityService k2EntityService;
	@Autowired(required=true)
	protected K2MethodService k2MethodService;
	@Override
	public EntityService  getService() { return service; }
	
	public static Dependency NULL = new DependencyBO();
	public DependencyBO() { super(null); }
    public DependencyBO(PersistenceState state) { super(state); }
    public DependencyBO(DependencyENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.DEPENDENCY; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Dependency Null() { return NULL; }
	
	protected DependencyENT entity;
	@Override
	public DependencyENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	// K2Entity field
	@Override
    public K2Entity getK2Entity() { if (isNull()) { return null; } return k2EntityService.getBO(getEntity().getK2Entity()); }
	@Override
	public void setK2Entity(K2Entity k2Entity) { if (isNull()) { return; } getEntity().setK2Entity(k2Entity.getEntity()); changed(); }
    	
	// K2Method field
	@Override
    public K2Method getK2Method() { if (isNull()) { return K2MethodBO.NULL; } return Nvl.nvl(k2MethodService.getBO(getEntity().getK2Method()), K2MethodBO.NULL); }
	@Override
    public void setK2Method(K2Method k2Method) { if (isNull()) { return; } getEntity().setK2Method(k2Method.getEntity()); changed(); }

	@Override
	public String getDependsOnPackageName() { if (isNull()) { return null; } return getEntity().getDependsOnPackageName(); }
	@Override
	public void setDependsOnPackageName(String dependesOnPackageName) { if (isNull()) { return; } getEntity().setDependsOnPackageName(dependesOnPackageName); changed(); }
	
	@Override
	public String getDependsOnComponentName() { if (isNull()) { return null; } return getEntity().getDependsOnComponentName(); }
	@Override
	public void setDependsOnComponentName(String dependsOnComponentName) { if (isNull()) { return; } getEntity().setDependsOnComponentName(dependsOnComponentName); changed(); }

	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new DependencyENT(); }
		if (Dependency.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((Dependency)source);
		}
	}
	@Override
	public ServiceList<K2Entity> getEntities() { return k2EntityService.listAll(); }
	@Override
	public ServiceList<K2Method> getEntityMethods() { return k2MethodService.listForEntity(getK2Entity()); }


}
