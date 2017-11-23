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
import com.k2.dev.dao.K2PermittedContentDAO;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.K2PermittedContent;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.bo.K2FieldBO;
import com.k2.dev.model.bo.K2PermittedContentBO;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2PermittedContentENT;
import com.k2.dev.model.entity.K2SnippetContainerENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2PermittedContentService;

@Service("K2PermittedContentService")
@Transactional
public class K2PermittedContentServiceImpl extends GenericEntityService<K2PermittedContentENT, Long, K2PermittedContent> implements K2PermittedContentService{

	@Autowired
	K2PermittedContentService permittedContentService;
	
	public static class Lists {
		
		private static abstract class K2PermitedContentServiceList extends GenericServiceList<K2PermittedContentENT, K2PermittedContent> implements ServiceList<K2PermittedContent> {
			protected K2PermittedContentDAO dao;
			protected K2PermittedContentService service;
			public K2PermitedContentServiceList(K2PermittedContentService service, K2PermittedContentDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.PERMITTED_CONTENT; }
		}

		public static class All extends K2PermitedContentServiceList implements ServiceList<K2PermittedContent> {
			public All(K2PermittedContentService service, K2PermittedContentDAO dao) { super(service, dao); }
			@Override public K2PermittedContent newBO() { return service.newBO(); }
			@Override public K2PermittedContent newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2PermittedContentENT> getList() { return dao.list(); }
			@Override protected K2PermittedContent getBO(K2PermittedContentENT entity) { return service.getBO(entity); }
		}

		public static class ForSnippetContainer extends K2PermitedContentServiceList implements ServiceList<K2PermittedContent> {
			private K2SnippetContainer snippetContainer;
			public ForSnippetContainer(K2PermittedContentService service, K2PermittedContentDAO dao, K2SnippetContainer snippetContainer) { 
				super(service, dao);
				this.snippetContainer = snippetContainer;
			}
			@Override public K2PermittedContent newBO() { return service.newBO(null, new Init.ListForSnippetContainer(snippetContainer.getEntity())); }
			@Override public K2PermittedContent newBO(Long id) { return service.newBO(id, new Init.ListForSnippetContainer(snippetContainer.getEntity())); }
			@Override protected List<K2PermittedContentENT> getList() { return dao.listForSnippetContainer(snippetContainer.getEntity()); }
			@Override protected K2PermittedContent getBO(K2PermittedContentENT entity) { return service.getBO(entity); }
		}
	}

	public static class Init {
		
		public static class ListForSnippetContainer extends EntityInitialValues<K2PermittedContentENT> {
			private K2SnippetContainerENT snippetContainer;
			public ListForSnippetContainer(K2SnippetContainerENT snippetContainer) {
				this.snippetContainer = snippetContainer;
			}
			@Override
			public void initialize(K2PermittedContentENT entity) {
				entity.setContainer(snippetContainer);
			}
		}
		
	}

	public K2PermittedContentServiceImpl() {}

	@Autowired
	private K2PermittedContentDAO dao;
	@Override
	protected K2PermittedContentDAO getDAO() { return dao; }

	@Override
	public ServiceList<K2PermittedContent> listForSnippetContainer(K2SnippetContainer snippetContainer) { return new Lists.ForSnippetContainer(this, dao, snippetContainer); }

	@Override
	public K2PermittedContent fetchForSnippetContainerAndName(K2SnippetContainer cont, String name) {
		return permittedContentService.getBO(dao.fetchForSnippetContainerAndName(cont.getEntity(), name));
	}

	@Override
	public K2PermittedContent nullBO() { return K2PermittedContentBO.NULL; }

	@Override
	public ServiceList<K2PermittedContent> listAll() { return new Lists.All(this, dao); }

	@Override
	public K2PermittedContent newBO(Long id, EntityInitialValues<K2PermittedContentENT> init) { 
		if (id == null) {
			return (K2PermittedContent) serviceContext.putBO(new K2PermittedContentBO(prepareNewEntity(new K2PermittedContentENT(), "EntityBinding.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2PermittedContent) serviceContext.putBO(new K2PermittedContentBO(prepareNewEntity(new K2PermittedContentENT(), id, init), PersistenceState.NEW)); 
		}
	}

	

	@Override
	public K2PermittedContent getBO(K2PermittedContentENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2PermittedContent) serviceContext.getBO(entity); }
		return (K2PermittedContent) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}

	@Override
	public K2PermittedContent newK2PermittedContent() { return super.newBO(); }
	@Override
	public K2PermittedContent fetchK2PermittedContent(Long id) { return super.fetch(id); }




}
