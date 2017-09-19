package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.Dependency;
import com.k2.dev.model.entity.DependencyENT;
import com.k2.dev.service.DependencyService;

@SuppressWarnings("rawtypes")
@Configurable
public class DependencyBO extends GenericServiceModel implements ServiceModel, Dependency {
	
	@Autowired(required=true)
	protected DependencyService service;
	@Override
	public EntityService  getService() { return service; }
	
	public static Dependency NULL = new DependencyBO();
	public DependencyBO() { super(null); }
    public DependencyBO(PersistenceState state) { super(state); }
    public DependencyBO(DependencyENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Dependency Null() { return NULL; }
	
	private DependencyENT entity;
	@Override
	public DependencyENT getEntity() { return entity; }
	
	@Override
	public Long getID() { if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public String getDependsOnPackageName() { if (isNull()) { return null; } return entity.getDependsOnPackageName(); }
	@Override
	public void setDependsOnPackageName(String dependesOnPackageName) { if (isNull()) { return; } entity.setDependsOnPackageName(dependesOnPackageName); changed(); }
	
	@Override
	public String getDependsOnComponentName() { if (isNull()) { return null; } return entity.getDependsOnComponentName(); }
	@Override
	public void setDependsOnComponentName(String dependsOnComponentName) { if (isNull()) { return; } entity.setDependsOnComponentName(dependsOnComponentName); changed(); }
		
}
