package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.Project;
import com.k2.dev.model.entity.ProjectENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ProjectService;

@SuppressWarnings("rawtypes")
@Configurable
public class ProjectBO extends GenericServiceModel implements ServiceModel, Project {
	
	@Autowired(required=true)
	protected ProjectService service;
	@Override
	public EntityService getService() { return service; }
	
	public static Project NULL = new ProjectBO();
	public ProjectBO() { super(null); }
    public ProjectBO(PersistenceState state) { super(state); }
    public ProjectBO(ProjectENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaEntity getMetaEntity() { return MetaModel.Entities.PROJECT; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Project Null() { return NULL; }
	
	private ProjectENT entity;
	@Override
	public ProjectENT getEntity() { return entity; }
	
	@Override
	public Long getID() { if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return entity.getName(); }
	@Override
	public void setName(String name) { if (isNull()) { return; } entity.setName(name); changed(); }
	
	@Override
	public String getDescription() { if (isNull()) { return null; } return entity.getName(); }
	@Override
	public void setDescription(String description) { if (isNull()) { return; } entity.setDescription(description); changed(); }
	
	@Override
	public String getFileSystemRoot() { if (isNull()) { return null; } return entity.getFileSystemRoot(); }
	@Override
	public void setFileSystemRoot(String fileSystemRoot) { if (isNull()) { return; } entity.setFileSystemRoot(fileSystemRoot); changed(); }
	

	
}
