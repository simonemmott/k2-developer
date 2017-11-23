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
import com.k2.dev.dao.K2SnippetBindingDAO;
import com.k2.dev.model.K2PermittedContent;
import com.k2.dev.model.K2SnippetBinding;
import com.k2.dev.model.bo.K2PermittedContentBO;
import com.k2.dev.model.bo.K2SnippetBindingBO;
import com.k2.dev.model.entity.K2PermittedContentENT;
import com.k2.dev.model.entity.K2SnippetBindingENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2SnippetBindingService;

@Service("K2SnippetBindingService")
@Transactional
public class K2SnippetBindingServiceImpl extends GenericEntityService<K2SnippetBindingENT, Long, K2SnippetBinding> implements K2SnippetBindingService{

	public static class Lists {
		
		private static abstract class K2SnippetBindingServiceList extends GenericServiceList<K2SnippetBindingENT, K2SnippetBinding> implements ServiceList<K2SnippetBinding> {
			protected K2SnippetBindingDAO dao;
			protected K2SnippetBindingService service;
			public K2SnippetBindingServiceList(K2SnippetBindingService service, K2SnippetBindingDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.SNIPPET_BINDING; }
		}

		public static class All extends K2SnippetBindingServiceList implements ServiceList<K2SnippetBinding> {
			public All(K2SnippetBindingService service, K2SnippetBindingDAO dao) { super(service, dao); }
			@Override public K2SnippetBinding newBO() { return service.newBO(); }
			@Override public K2SnippetBinding newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2SnippetBindingENT> getList() { return dao.list(); }
			@Override protected K2SnippetBinding getBO(K2SnippetBindingENT entity) { return service.getBO(entity); }
		}

	}

	public K2SnippetBindingServiceImpl() {}

	@Autowired
	private K2SnippetBindingDAO dao;
	@Override
	protected K2SnippetBindingDAO getDAO() { return dao; }
	@Override
	public K2SnippetBinding nullBO() { return K2SnippetBindingBO.NULL; }
	
	@Override
	public ServiceList<K2SnippetBinding> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public K2SnippetBinding newBO(Long id, EntityInitialValues<K2SnippetBindingENT> init) { 
		if (id == null) {
			return (K2SnippetBinding) serviceContext.putBO(new K2SnippetBindingBO(prepareNewEntity(new K2SnippetBindingENT(), "EntityBinding.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2SnippetBinding) serviceContext.putBO(new K2SnippetBindingBO(prepareNewEntity(new K2SnippetBindingENT(), id, init), PersistenceState.NEW)); 
		}
	}


	
	@Override
	public K2SnippetBinding getBO(K2SnippetBindingENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2SnippetBinding) serviceContext.getBO(entity); }
		return (K2SnippetBinding) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2SnippetBinding newK2SnippetBinding() { return super.newBO(); }
	@Override
	public K2SnippetBinding fetchK2SnippetBinding(Long id) { return super.fetch(id); }


}
