package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.DependencyDAO;
import com.k2.dev.model.Component;
import com.k2.dev.model.Dependency;
import com.k2.dev.model.bo.ComponentBO;
import com.k2.dev.model.bo.DependencyBO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.DependencyENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.DependencyService;

@Service("dependencyService")
@Transactional
public class DependencyServiceImpl extends GenericEntityService<DependencyENT, Long, Dependency> implements DependencyService{

	public static class Lists {
		
		private static abstract class DependencyServiceList extends GenericServiceList<DependencyENT, Dependency> implements ServiceList<Dependency> {
			protected DependencyDAO dao;
			protected DependencyService service;
			public DependencyServiceList(DependencyService service, DependencyDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaEntity getMetaEntity() { return MetaModel.Entities.DEPENDENCY; }
		}

		public static class All extends DependencyServiceList implements ServiceList<Dependency> {
			public All(DependencyService service, DependencyDAO dao) { super(service, dao); }
			@Override public Dependency newBO() { return service.newBO(); }
			@Override public Dependency newBO(Long id) { return service.newBO(id); }
			@Override protected List<DependencyENT> getList() { return dao.list(); }
			@Override protected Dependency getBO(DependencyENT entity) { return service.getBO(entity); }
		}
	}

	public DependencyServiceImpl() {}
	
	@Autowired
	DependencyDAO dao;
	@Override
	protected DependencyDAO getDAO() { return dao; }
	@Override
	public Dependency nullBO() { return DependencyBO.NULL; }
	
	@Override
	public ServiceList<Dependency> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public Dependency newBO(Long id, EntityInitialValues<DependencyENT> init) { 
		if (id == null) {
			return (Dependency) serviceContext.putBO(new DependencyBO(prepareNewEntity(new DependencyENT(), "Component.ID", init), PersistenceState.NEW)); 
		} else {
			return (Dependency) serviceContext.putBO(new DependencyBO(prepareNewEntity(new DependencyENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public Dependency getBO(DependencyENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (Dependency) serviceContext.getBO(entity); }
		return (Dependency) serviceContext.putBO(new DependencyBO(entity, PersistenceState.PERSISTED));
	}
	@Override
	public Dependency newDependency() { return super.newBO(); }
	@Override
	public Dependency fetchDependency(Long id) { return super.fetch(id); }


}
