package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.ComponentDAO;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.ComponentBO;
import com.k2.dev.model.bo.K2ServiceBO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;

@Service("ComponentService")
@Transactional
public class ComponentServiceImpl extends GenericEntityService<ComponentENT, Long, Component> implements ComponentService {

	public static class Lists {
		
		private static abstract class ComponentServiceList extends GenericServiceList<ComponentENT, Component> implements ServiceList<Component> {
			protected ComponentDAO dao;
			protected ComponentService service;
			public ComponentServiceList(ComponentService service, ComponentDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.COMPONENT; }
		}

		public static class All extends ComponentServiceList implements ServiceList<Component> {
			public All(ComponentService service, ComponentDAO dao) { super(service, dao); }
			@Override public Component newBO() { return service.newBO(); }
			@Override public Component newBO(Long id) { return service.newBO(id); }
			@Override protected List<ComponentENT> getList() { return dao.list(); }
			@Override protected Component getBO(ComponentENT entity) { return service.getBO(entity); }
		}

		public static class ForService extends ComponentServiceList implements ServiceList<Component> {
			private K2Service k2Service;
			public ForService(ComponentService service, ComponentDAO dao, K2Service k2Service) { 
				super(service, dao);
				this.k2Service = k2Service;
			}
			@Override public Component newBO() { return service.newBO(null, new Init.ListForService(k2Service.getEntity())); }
			@Override public Component newBO(Long id) { return service.newBO(id, new Init.ListForService(k2Service.getEntity())); }
			@Override protected List<ComponentENT> getList() { return dao.listForService(k2Service.getEntity()); }
			@Override protected Component getBO(ComponentENT entity) { return service.getBO(entity); }
		}

}
	public static class Init {
		
		public static class ListForService extends EntityInitialValues<ComponentENT> {
			private K2ServiceENT k2Service;
			public ListForService(K2ServiceENT k2Service) {
				this.k2Service = k2Service;
			}
			@Override public void initialize(ComponentENT entity) { entity.setK2Service(k2Service); }
		}
		
	}


	public ComponentServiceImpl() {}
	
	@Autowired
	ComponentDAO dao;
	@Override
	protected ComponentDAO getDAO() { return dao; }

	
	@Override
	public Component nullBO() { return ComponentBO.NULL; }
	@Override
	public Component getBO(ComponentENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (Component) serviceContext.getBO(entity); }
		return (Component) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public Component newBO(Long id, EntityInitialValues<ComponentENT> init) { 
		if (id == null) {
			return (Component) serviceContext.putBO(new ComponentBO(prepareNewEntity(new ComponentENT(), "Component.ID", init), PersistenceState.NEW)); 
		} else {
			return (Component) serviceContext.putBO(new ComponentBO(prepareNewEntity(new ComponentENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public Component newComponent() { return super.newBO(); }
	@Override
	public Component fetchComponent(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<Component> listAll() { return new Lists.All(this, dao); }


	@Override
	public ServiceList<Component> listForService(K2Service k2Service) { return new Lists.ForService(this, dao, k2Service); }


}
