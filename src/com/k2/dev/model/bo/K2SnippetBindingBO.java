package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetBinding;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.entity.K2SnippetBindingENT;
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
	public boolean isNull() { return (this == NULL); }
	@Override
	public K2SnippetBinding Null() { return NULL; }
	
	private K2SnippetBindingENT entity;
	@Override public K2SnippetBindingENT getEntity() { return entity; }

	@Override
	public Long getID() { return entity.getID(); }
	@Override
	public void setID(Long id) { entity.setID(id); changed(); }
	
	@Override
	public K2Snippet getWidget() { if (isNull()) { return K2SnippetBO.NULL; } return snippetService.getBO(entity.getWidget()); }
	@Override
	public void setWidget(K2Snippet widget) { if (isNull()) { return; } entity.setWidget(widget.getEntity()); changed(); }

	@Override
	public K2SnippetParameter getBoundParameter() { if (isNull()) { return K2SnippetParameterBO.NULL; } return snippetParameterService.getBO(entity.getBoundParameter()); }
	@Override
	public void setBoundParameter(K2SnippetParameter boundParameter) { if (isNull()) { return; } entity.setBoundParameter(boundParameter.getEntity()); changed(); }


}
