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
import com.k2.dev.dao.K2SnippetParameterDAO;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.Template;
import com.k2.dev.model.bo.K2SnippetContainerBO;
import com.k2.dev.model.bo.K2SnippetParameterBO;
import com.k2.dev.model.entity.K2SnippetContainerENT;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.entity.K2SnippetParameterENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2SnippetParameterService;

@Service("snippetParameterService")
@Transactional
public class K2SnippetParameterServiceImpl extends GenericEntityService<K2SnippetParameterENT, Long, K2SnippetParameter> implements K2SnippetParameterService{

	public static class Lists {
		
		private static abstract class K2SnippetParameterServiceList extends GenericServiceList<K2SnippetParameterENT, K2SnippetParameter> implements ServiceList<K2SnippetParameter> {
			protected K2SnippetParameterDAO dao;
			protected K2SnippetParameterService service;
			public K2SnippetParameterServiceList(K2SnippetParameterService service, K2SnippetParameterDAO dao) { 
				this.service = service; 
				this.dao = dao; 
			}
			@Override public MetaEntity getMetaEntity() { return MetaModel.Entities.SNIPPET_PARAMETER; }
		}
		public static class All extends K2SnippetParameterServiceList implements ServiceList<K2SnippetParameter> {
			public All(K2SnippetParameterService service, K2SnippetParameterDAO dao) { super(service, dao); }
			@Override public K2SnippetParameter newBO() { return service.newBO(); }
			@Override public K2SnippetParameter newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2SnippetParameterENT> getList() { return dao.list(); }
			@Override protected K2SnippetParameter getBO(K2SnippetParameterENT entity) { return service.getBO(entity); }
		}

		public static class ForSnippet extends K2SnippetParameterServiceList implements ServiceList<K2SnippetParameter> {
			private K2Snippet snippet;
			public ForSnippet(K2SnippetParameterService service, K2SnippetParameterDAO dao, K2Snippet snippet) { 
				super(service, dao); 
				this.snippet = snippet;
			}
			@Override public K2SnippetParameter newBO() { return service.newBO(null, new Init.ListForSnippet(snippet.getEntity())); }
			@Override public K2SnippetParameter newBO(Long id) { return service.newBO(id, new Init.ListForSnippet(snippet.getEntity())); }
			@Override protected List<K2SnippetParameterENT> getList() { return dao.listForSnippet(snippet.getEntity()); }
			@Override protected K2SnippetParameter getBO(K2SnippetParameterENT entity) { return service.getBO(entity); }
		}

		public static class ForTemplate extends K2SnippetParameterServiceList implements ServiceList<K2SnippetParameter> {
			private Template template;
			public ForTemplate(K2SnippetParameterService service, K2SnippetParameterDAO dao, Template template) { 
				super(service, dao); 
				this.template = template;
			}
			@Override public K2SnippetParameter newBO() { return service.newBO(null, new Init.ListForSnippet(template.getEntity())); }
			@Override public K2SnippetParameter newBO(Long id) { return service.newBO(id, new Init.ListForSnippet(template.getEntity())); }
			@Override protected List<K2SnippetParameterENT> getList() { return dao.listForSnippet(template.getEntity()); }
			@Override protected K2SnippetParameter getBO(K2SnippetParameterENT entity) { return service.getBO(entity); }
		}
}
	public static class Init {
		
		public static class ListForSnippet extends EntityInitialValues<K2SnippetParameterENT> {
			private K2SnippetENT snippet;
			public ListForSnippet(K2SnippetENT snippet) {
				this.snippet = snippet;
			}
			@Override
			public void initialize(K2SnippetParameterENT entity) {
				entity.setWidget(snippet);
			}
		}
		
	}

	public K2SnippetParameterServiceImpl() {}

	@Autowired
	private K2SnippetParameterDAO dao;
	@Override
	protected K2SnippetParameterDAO getDAO() { return dao; }
	@Override
	public K2SnippetParameter nullBO() { return K2SnippetParameterBO.NULL; }

	@Override
	public K2SnippetParameter newBO(Long id, EntityInitialValues<K2SnippetParameterENT> init) { 
		if (id == null) {
			return (K2SnippetParameter) serviceContext.putBO(new K2SnippetParameterBO(prepareNewEntity(new K2SnippetParameterENT(), "EntityBinding.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2SnippetParameter) serviceContext.putBO(new K2SnippetParameterBO(prepareNewEntity(new K2SnippetParameterENT(), id, init), PersistenceState.NEW)); 
		}
	}


	@Override
	public ServiceList<K2SnippetParameter> listForSnippet(K2Snippet snippet) { return new Lists.ForSnippet(this, dao, snippet); }

	@Override
	public K2SnippetParameter fetchForSnippetAndName(K2Snippet snippet, String name) { return getBO(dao.fetchForSnippetAndName(snippet.getEntity(), name)); }
	@Override
	public ServiceList<K2SnippetParameter> listAll() { return new Lists.All(this, dao); }
	@Override
	public K2SnippetParameter getBO(K2SnippetParameterENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2SnippetParameter) serviceContext.getBO(entity); }
		return (K2SnippetParameter) serviceContext.putBO(new K2SnippetParameterBO(entity, PersistenceState.PERSISTED));
	}
	@Override
	public ServiceList<K2SnippetParameter> listForTemplate(Template template) { return new Lists.ForTemplate(this, dao, template); }
	@Override
	public K2SnippetParameter newK2SnippetParameter() { return super.newBO(); }
	@Override
	public K2SnippetParameter fetchK2SnippetParameter(Long id) { return super.fetch(id); }

}
