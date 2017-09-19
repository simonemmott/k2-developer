package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.ProjectDAO;
import com.k2.dev.model.Project;
import com.k2.dev.model.bo.ProjectBO;
import com.k2.dev.model.entity.ProjectENT;
import com.k2.dev.service.ProjectService;

@Service("projectService")
@Transactional
public class ProjectServiceImpl extends GenericEntityService<ProjectENT, Long, Project> implements ProjectService{

	public static class Lists {
		
		private static abstract class ProjectServiceList extends GenericServiceList<ProjectENT, Project> implements ServiceList<Project> {
			protected ProjectDAO dao;
			protected ProjectService service;
			public ProjectServiceList(ProjectService service, ProjectDAO dao) { this.service = service; this.dao = dao; }
		}

		public static class All extends ProjectServiceList implements ServiceList<Project> {
			public All(ProjectService service, ProjectDAO dao) { super(service, dao); }
			@Override public Project newBO() { return service.newBO(); }
			@Override protected List<ProjectENT> getList() { return dao.list(); }
			@Override protected Project getBO(ProjectENT entity) { return service.getBO(entity); }
		}

	}

	public ProjectServiceImpl() {}

	@Autowired
	private ProjectDAO dao;
	@Override
	protected ProjectDAO getDAO() { return dao; }
	@Override
	protected Project nullBO() { return ProjectBO.NULL; }
	
	@Override
	public ServiceList<Project> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public Project newBO(EntityInitialValues<ProjectENT> init) {
		return (Project) serviceContext.putBO(new ProjectBO(prepareNewEntity(new ProjectENT(), "Project.ID", init), PersistenceState.NEW)); 
	}
	@Override
	public Project getBO(ProjectENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (Project) serviceContext.getBO(entity); }
		return (Project) serviceContext.putBO(new ProjectBO(entity, PersistenceState.PERSISTED));
	}
	@Override
	public Project newProject() { return super.newBO(); }
	@Override
	public Project fetchProject(Long id) { return super.fetch(id); }
	@Override
	public Project fetchForName(String name) { return getBO(dao.fetchForName(name)); }


}
