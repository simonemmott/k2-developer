package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.dev.model.Project;
import com.k2.dev.model.entity.ProjectENT;

public interface ProjectService extends EntityService<ProjectENT, Long, Project>  {

	public Project newProject();
	public Project fetchProject(Long id);
	public Project fetchForName(String string);

}
