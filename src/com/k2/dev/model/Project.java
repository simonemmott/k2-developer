package com.k2.dev.model;

import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ProjectENT;

@SuppressWarnings("rawtypes")
public interface Project extends ServiceModel {
	
	@Override public ProjectENT getEntity();
	@Override public Project Null();

	public Long getID();

	public void setID(Long id);

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

	public String getFileSystemRoot();

	public void setFileSystemRoot(String fileSystemRoot);

}