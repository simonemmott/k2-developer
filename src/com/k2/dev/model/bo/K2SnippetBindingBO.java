package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.K2PermittedContent;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetBinding;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.entity.K2PermittedContentENT;
import com.k2.dev.model.entity.K2SnippetBindingENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2SnippetBindingService;
import com.k2.dev.service.K2SnippetParameterService;
import com.k2.dev.service.K2SnippetService;

@SuppressWarnings("rawtypes")
@Configurable
public class K2SnippetBindingBO extends GenericServiceModel implements ServiceModel, K2SnippetBinding {
	
	@Autowired(required=true)
	protected K2SnippetBindingService service;
	@Override
	public EntityService  getService() { return service; }
	
	@Autowired
	private K2SnippetService snippetService;
	
	@Autowired
	private K2SnippetParameterService snippetParameterService;
	
	public static K2SnippetBinding NULL = new K2SnippetBindingBO();
	public K2SnippetBindingBO() { super(null); }
	public K2SnippetBindingBO(PersistenceState state) { super(state); }
	public K2SnippetBindingBO(K2SnippetBindingENT entity, PersistenceState state) { super(state); this.entity = entity; }
	
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.SNIPPET_BINDING; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2SnippetBinding Null() { return NULL; }
	
	protected K2SnippetBindingENT entity;
	@Override public K2SnippetBindingENT getEntity() { return entity; }

	@Override
	public Long getId() { return getEntity().getId(); }
	@Override
	public void setId(Long id) { getEntity().setId(id); changed(); }
	
	@Override
	public K2Snippet getWidget() { if (isNull()) { return K2SnippetBO.NULL; } return Nvl.nvl(snippetService.getBO(getEntity().getWidget()), K2SnippetBO.NULL); }
	@Override
	public void setWidget(K2Snippet widget) { if (isNull()) { return; } getEntity().setWidget(widget.getEntity()); changed(); }

	@Override
	public K2SnippetParameter getBoundParameter() { if (isNull()) { return K2SnippetParameterBO.NULL; } return Nvl.nvl(snippetParameterService.getBO(getEntity().getBoundParameter()), K2SnippetParameterBO.NULL); }
	@Override
	public void setBoundParameter(K2SnippetParameter boundParameter) { if (isNull()) { return; } getEntity().setBoundParameter(boundParameter.getEntity()); changed(); }

	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new K2SnippetBindingENT(); }
		if (K2SnippetBinding.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((K2SnippetBinding)source);
		}
	}



}
