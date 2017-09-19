package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.entity.TemplateENT;
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
	public boolean isNull() { return (this == NULL); }
	@Override
	public Template Null() { return NULL; }
	
	private TemplateENT entity;
	@Override
	public TemplateENT getEntity() { return entity; }

/*	
	@Override
	public Long getID() { if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public String getName() { if (isNull()) { return null; } return entity.getName(); }
	@Override
	public void setName(String name) { entity.setName(name); changed(); }

	@Override
	public String getPackageName() { if (isNull()) { return null; } return entity.getPackageName(); }
	@Override
	public void setPackageName(String packageName) { entity.setPackageName(packageName); changed(); }

	@Override
	public String getClassName() { if (isNull()) { return null; } return entity.getClassName(); }
	@Override
	public void setClassName(String className) { entity.setClassName(className); changed(); }

	@Override
	public String getDescription() { if (isNull()) { return null; } return entity.getDescription(); }
	@Override
	public void setDescription(String description) { entity.setDescription(description); changed(); }
*/	
	@Override
	public K2Snippet getPopulatesSnippet() { if (isNull()) { return null; } return snippetService.getBO(entity.getPopulatesSnippet()); }
	@Override
	public void setPopulatesSnippet(K2Snippet snippet) { if (isNull()) { return; } entity.setPopulatesSnippet(snippet.getEntity()); changed(); }
	
	@Override
	public ServiceList<TemplateBinding> getTemplateBindings() { return templateBindingService.listForTemplate(this); }
	
	@Override
	public ServiceList<TemplateContent> getContents() { return templateContentService.listForTemplate(this); }
/*	
	@Override
	public ServiceList<K2SnippetParameter> getParameters() { return snippetParameterService.listForTemplate(this); }
	
	@Override
	public ServiceList<K2SnippetContainer> getContainers() { return snippetContainerService.listForTemplate(this); }

	@Override
	public K2SnippetContainer getContainerForAlias(String alias) { if (isNull()) { return K2SnippetContainerBO.NULL; } return snippetContainerService.fetchForSnippetAndAlias(this, alias); }
	@Override
	public K2SnippetParameter getParameterForName(String name) { return snippetParameterService.fetchForSnippetAndName(this, name); }
*/	
}
