package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.dev.dao.K2SnippetDAO;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.bo.K2SnippetBO;
import com.k2.dev.model.bo.K2SnippetParameterBO;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.entity.K2SnippetParameterENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2SnippetService;

@Service("K2SnippetService")
public class K2SnippetServiceImpl extends GenericEntityService<K2SnippetENT, Long, K2Snippet> implements K2SnippetService{
	
	private static class Lists {
		private static abstract class K2SnippetServiceList extends GenericServiceList<K2SnippetENT, K2Snippet> implements ServiceList<K2Snippet> {
			protected K2SnippetDAO dao;
			protected K2SnippetService service;
			public K2SnippetServiceList(K2SnippetService service, K2SnippetDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2SNIPPET; }
		}
		public static class All extends K2SnippetServiceList implements ServiceList<K2Snippet> {
			public All(K2SnippetService service, K2SnippetDAO dao) { super(service, dao); }
			@Override public K2Snippet newBO() { return service.newBO(); }
			@Override public K2Snippet newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2SnippetENT> getList() { return dao.list(); }
			@Override protected K2Snippet getBO(K2SnippetENT entity) { return service.getBO(entity); }
		}
	}
	
	public K2SnippetServiceImpl() {}

	@Autowired
	private K2SnippetDAO dao;
	@Override
	protected K2SnippetDAO getDAO() { return dao; }

	@Override
	public K2Snippet nullBO() { return K2SnippetBO.NULL; }
	@Override
	public K2Snippet getBO(K2SnippetENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2Snippet) serviceContext.getBO(entity); }
		return (K2Snippet) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	
	@Override
	public K2Snippet newBO(Long id, EntityInitialValues<K2SnippetENT> init) { 
		if (id == null) {
			return (K2Snippet) serviceContext.putBO(new K2SnippetBO(prepareNewEntity(new K2SnippetENT(), "EntityBinding.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2Snippet) serviceContext.putBO(new K2SnippetBO(prepareNewEntity(new K2SnippetENT(), id, init), PersistenceState.NEW)); 
		}
	}


	@Override
	public K2Snippet fetchForName(String name) { return getBO(dao.fetchForName(name)); }

	@Override
	public ServiceList<K2Snippet> listAll() { return new Lists.All(this, dao); }

	@Override
	public K2Snippet newK2Snippet() { return super.newBO(); }
	@Override
	public K2Snippet fetchK2Snippet(Long id) { return super.fetch(id); }


}
