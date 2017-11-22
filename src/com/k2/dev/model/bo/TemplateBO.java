package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.entity.TemplateBindingENT;
import com.k2.dev.model.entity.TemplateENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2SnippetContainerService;
import com.k2.dev.service.K2SnippetParameterService;
import com.k2.dev.service.K2SnippetService;
import com.k2.dev.service.TemplateBindingService;
import com.k2.dev.service.TemplateContentService;
import com.k2.dev.service.TemplateService;

@SuppressWarnings("rawtypes")
@Configurable
public class TemplateBO extends K2SnippetBO implements ServiceModel, Template, K2Snippet {
	
	@Autowired(required=true)
	protected TemplateService service;
	@Override
	public EntityService getService() { return service; }
	
	@Autowired(required=true)
	K2SnippetService snippetService;
	
	@Autowired(required=true)
	TemplateBindingService templateBindingService;
	
	@Autowired(required=true)
	TemplateContentService templateContentService;
	
	@Autowired(required=true)
	K2SnippetParameterService snippetParameterService;
	
	@Autowired
	K2SnippetContainerService snippetContainerService;
	
	public static Template NULL = new TemplateBO();
	public TemplateBO() { super(null); }
    public TemplateBO(PersistenceState state) { super(state); }
    public TemplateBO(TemplateENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.TEMPLATE; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public Template Null() { return NULL; }
	
	@Override
	public TemplateENT getEntity() { return (TemplateENT)entity; }

	@Override
	public K2Snippet getPopulatesSnippet() { if (isNull()) { return K2SnippetBO.NULL; } return Nvl.nvl(snippetService.getBO(getEntity().getPopulatesSnippet()), K2SnippetBO.NULL); }
	@Override
	public void setPopulatesSnippet(K2Snippet snippet) { if (isNull()) { return; } getEntity().setPopulatesSnippet(snippet.getEntity()); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new TemplateENT(); }
		if (Template.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((Template)source);
		} else {
			super.clone(source);
		}
	}


	
	@Override
	public ServiceList<TemplateBinding> getTemplateBindings() { return templateBindingService.listForTemplate(this); }
	
	@Override
	public ServiceList<TemplateContent> getContents() { return templateContentService.listForTemplate(this); }
	
}
