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
import com.k2.dev.dao.ComponentDAO;
import com.k2.dev.model.Component;
import com.k2.dev.model.bo.ComponentBO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.entity.K2SnippetContainerENT;
import com.k2.dev.service.ComponentService;

@Service("componentService")
@Transactional
public class ComponentServiceImpl extends GenericEntityService<ComponentENT, Long, Component> implements ComponentService {

	public static class Lists {
		
		private static abstract class ComponentServiceList extends GenericServiceList<ComponentENT, Component> implements ServiceList<Component> {
			protected ComponentDAO dao;
			protected ComponentService service;
			public ComponentServiceList(ComponentService service, ComponentDAO dao) { this.service = service; this.dao = dao; }
		}

		public static class All extends ComponentServiceList implements ServiceList<Component> {
			public All(ComponentService service, ComponentDAO dao) { super(service, dao); }
			@Override public Component newBO() { return service.newBO(); }
			@Override protected List<ComponentENT> getList() { return dao.list(); }
			@Override protected Component getBO(ComponentENT entity) { return service.getBO(entity); }
		}
	}
	public static class Init {
		
		public static class ListForSnippet extends EntityInitialValues<K2SnippetContainerENT> {
			private K2SnippetENT snippet;
			public ListForSnippet(K2SnippetENT snippet) {
				this.snippet = snippet;
			}
			@Override public void initialize(K2SnippetContainerENT entity) { entity.setWidget(snippet); }
		}
		
	}


	public ComponentServiceImpl() {}
	
	@Autowired
	ComponentDAO dao;
	@Override
	protected ComponentDAO getDAO() { return dao; }

	
	@Override
	protected Component nullBO() { return ComponentBO.NULL; }
	@Override
	public Component getBO(ComponentENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (Component) serviceContext.getBO(entity); }
		return (Component) serviceContext.putBO(new ComponentBO(entity, PersistenceState.PERSISTED));
	}
	@Override
	public Component newBO(EntityInitialValues<ComponentENT> init) { 
		return (Component) serviceContext.putBO(new ComponentBO(prepareNewEntity(new ComponentENT(), "Component.ID", init), PersistenceState.NEW)); 
	}

	@Override
	public Component newComponent() { return super.newBO(); }
	@Override
	public Component fetchComponent(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<Component> listAll() { return new Lists.All(this, dao); }


}
