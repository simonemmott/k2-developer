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
import com.k2.dev.dao.K2SnippetContainerDAO;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetBinding;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.Template;
import com.k2.dev.model.bo.K2SnippetBindingBO;
import com.k2.dev.model.bo.K2SnippetContainerBO;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.entity.K2SnippetBindingENT;
import com.k2.dev.model.entity.K2SnippetContainerENT;
import com.k2.dev.service.K2SnippetContainerService;

@Service("K2SnippetContainerService")
@Transactional
public class K2SnippetContainerServiceImpl extends GenericEntityService<K2SnippetContainerENT, Long, K2SnippetContainer> implements K2SnippetContainerService{
	public static class Lists {
		
		private static abstract class K2SnippetContainerServiceList extends GenericServiceList<K2SnippetContainerENT, K2SnippetContainer> implements ServiceList<K2SnippetContainer> {
			protected K2SnippetContainerDAO dao;
			protected K2SnippetContainerService service;
			public K2SnippetContainerServiceList(K2SnippetContainerService service, K2SnippetContainerDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2SNIPPETCONTAINER; }
		}

		public static class All extends K2SnippetContainerServiceList implements ServiceList<K2SnippetContainer> {
			public All(K2SnippetContainerService service, K2SnippetContainerDAO dao) { super(service, dao); }
			@Override public K2SnippetContainer newBO() { return service.newBO(); }
			@Override public K2SnippetContainer newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2SnippetContainerENT> getList() { return dao.list(); }
			@Override protected K2SnippetContainer getBO(K2SnippetContainerENT entity) { return service.getBO(entity); }
		}

		public static class ForSnippet extends K2SnippetContainerServiceList implements ServiceList<K2SnippetContainer> {
			private K2Snippet snippet;
			public ForSnippet(K2SnippetContainerService service, K2SnippetContainerDAO dao, K2Snippet snippet) { 
				super(service, dao);
				this.snippet = snippet;
			}
			@Override public K2SnippetContainer newBO() { return service.newBO(null, new Init.ListForSnippet(snippet.getEntity())); }
			@Override public K2SnippetContainer newBO(Long id) { return service.newBO(id, new Init.ListForSnippet(snippet.getEntity())); }
			@Override protected List<K2SnippetContainerENT> getList() { return dao.listForSnippet(snippet.getEntity()); }
			@Override protected K2SnippetContainer getBO(K2SnippetContainerENT entity) { return service.getBO(entity); }
		}

		public static class ForTemplate extends K2SnippetContainerServiceList implements ServiceList<K2SnippetContainer> {
			private Template template;
			public ForTemplate(K2SnippetContainerService service, K2SnippetContainerDAO dao, Template template) { 
				super(service, dao);
				this.template = template;
			}
			@Override public K2SnippetContainer newBO() { return service.newBO(null, new Init.ListForSnippet(template.getEntity())); }
			@Override public K2SnippetContainer newBO(Long id) { return service.newBO(id, new Init.ListForSnippet(template.getEntity())); }
			@Override protected List<K2SnippetContainerENT> getList() { return dao.listForSnippet(template.getEntity()); }
			@Override protected K2SnippetContainer getBO(K2SnippetContainerENT entity) { return service.getBO(entity); }
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

	public K2SnippetContainerServiceImpl() {}

	@Autowired
	private K2SnippetContainerDAO dao;
	@Override
	protected K2SnippetContainerDAO getDAO() { return dao; }

	@Override
	public ServiceList<K2SnippetContainer> listForSnippet(K2Snippet snippet) {
		return new Lists.ForSnippet(this, dao, snippet);
	}

	@Override
	public K2SnippetContainer fetchForSnippetAndAlias(K2Snippet snippet, String alias) {
		return getBO(dao.fetchForSnippetAndAlias(snippet.getEntity(), alias));
	}

	@Override
	public K2SnippetContainer nullBO() { return K2SnippetContainerBO.NULL; }

	@Override
	public ServiceList<K2SnippetContainer> listAll() { return new Lists.All(this, dao); }

	@Override
	public K2SnippetContainer newBO(Long id, EntityInitialValues<K2SnippetContainerENT> init) { 
		if (id == null) {
			return (K2SnippetContainer) serviceContext.putBO(new K2SnippetContainerBO(prepareNewEntity(new K2SnippetContainerENT(), "EntityBinding.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2SnippetContainer) serviceContext.putBO(new K2SnippetContainerBO(prepareNewEntity(new K2SnippetContainerENT(), id, init), PersistenceState.NEW)); 
		}
	}


	@Override
	public K2SnippetContainer getBO(K2SnippetContainerENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2SnippetContainerBO) serviceContext.getBO(entity); }
		return (K2SnippetContainerBO) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}

	@Override
	public ServiceList<K2SnippetContainer> listForTemplate(Template template) { return new Lists.ForTemplate(this, dao, template); }

	@Override
	public K2SnippetContainer newK2SnippetContainer() { return super.newBO(); }
	@Override
	public K2SnippetContainer fetchK2SnippetContainer(Long id) { return super.fetch(id); }

}
