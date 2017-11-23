package com.k2.dev.model;

import java.io.File;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.ProjectENT;

@SuppressWarnings("rawtypes")
public interface Project extends ServiceModel, ID {
	
	@Override public ProjectENT getEntity();
	@Override public Project Null();

	public Long getId();

	public void setId(Long id);

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

	public String getFileSystemRoot();

	public void setFileSystemRoot(String fileSystemRoot);
	boolean verifyProjectRoot();
	void createProjectRoot();
	boolean verifyPackageDirectory(String packageName);
	void createPackageDirectory(String packageName);
	public File getPackageDirectory(String packageName);
	public File getJavaFile(String packageName, String component);

}