package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.nulls.Nvl;
import com.k2.common.service.EntityService;
import com.k2.common.service.GenericServiceModel;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.Project;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.entity.ProjectENT;
import com.k2.dev.model.entity.TemplateContentENT;
import com.k2.dev.model.entity.TemplateContentENT.Types;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2PermittedContentService;
import com.k2.dev.service.K2SnippetContainerService;
import com.k2.dev.service.K2SnippetService;
import com.k2.dev.service.TemplateBindingService;
import com.k2.dev.service.TemplateContentService;
import com.k2.dev.service.TemplateService;

@SuppressWarnings("rawtypes")
@Configurable
public class TemplateContentBO extends GenericServiceModel implements ServiceModel, TemplateContent {
	
	@Autowired(required=true)
	protected TemplateContentService service;
	@Override
	public EntityService getService() { return service; }
	
	@Autowired(required=true)
	TemplateService templateService;
	
	@Autowired(required=true)
	K2SnippetService snippetService;
	
	@Autowired(required=true)
	K2SnippetContainerService snippetContainerService;
	
	@Autowired(required=true)
	K2PermittedContentService permittedContentService;
	
	@Autowired
	TemplateBindingService templateBindingService;
	
	public static TemplateContent NULL = new TemplateContentBO();
	public TemplateContentBO() { super(null); }
    public TemplateContentBO(PersistenceState state) { super(state); }
    public TemplateContentBO(TemplateContentENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaModelEntity getMetaEntity() { return MetaModel.Entities.TEMPLATE_CONTENT; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public TemplateContent Null() { return NULL; }
	
	protected TemplateContentENT entity;
	@Override
	public TemplateContentENT getEntity() { return entity; }
	
	@Override
	public Long getId() { if (isNull()) { return null; } return getEntity().getId(); }
	@Override
	public void setId(Long id) { if (isNull()) { return; } getEntity().setId(id); changed(); }
	
	@Override
	public String getAlias() { if (isNull()) { return null; } return getEntity().getAlias(); }
	@Override
	public void setAlias(String alias) { if (isNull()) { return; } getEntity().setAlias(alias); changed(); }
		
	@Override
	public Template getTemplate() { if (isNull()) { return TemplateBO.NULL; } return Nvl.nvl(templateService.getBO(getEntity().getTemplate()), TemplateBO.NULL); }
	@Override
	public void setTemplate(Template template) { if (isNull()) { return; } getEntity().setTemplate(template.getEntity()); changed(); }
	
	@Override
	public K2Snippet getContainedSnippet() { if (isNull()) { return K2SnippetBO.NULL; } return Nvl.nvl(snippetService.getBO(getEntity().getContainedSnippet()), K2SnippetBO.NULL); }
	@Override
	public void setContainedSnippet(K2Snippet snippet) { if (isNull()) { return; } getEntity().setContainedSnippet(snippet.getEntity()); changed(); }
	
	@Override
	public K2SnippetContainer getInContainer() { if (isNull()) { return K2SnippetContainerBO.NULL; } return Nvl.nvl(snippetContainerService.getBO(getEntity().getInContainer()), K2SnippetContainerBO.NULL); }
	@Override
	public void setInContainer(K2SnippetContainer inContainer) { if (isNull()) { return; } getEntity().setInContainer(inContainer.getEntity()); changed(); }
	
	@Override
	public K2SnippetContainer getFromContainer() { if (isNull()) { return K2SnippetContainerBO.NULL; } return Nvl.nvl(snippetContainerService.getBO(getEntity().getFromContainer()), K2SnippetContainerBO.NULL); }
	@Override
	public void setFromContainer(K2SnippetContainer fromContainer) { if (isNull()) { return; } getEntity().setFromContainer(fromContainer.getEntity()); changed(); }

	@Override
	public Types.Type getType() { if (isNull()) { return null; } return getEntity().getType(); }
	@Override
	public void setType(Types.Type type) {  if (isNull()) { return; } getEntity().setType(type); changed(); }
	
	@Override
	public ServiceList<TemplateContent> getContents() { return service.listForContent(this); }
	
	@Override
	public TemplateContent getParentContent() { return service.getBO(getEntity().getParentContent()); }
	@Override
	public void setParentContent(TemplateContent parentContent) { if (isNull()) { return ; } getEntity().setParentContent(parentContent.getEntity()); changed(); }
	
	@Override
	public void clone(ServiceModel source) {
		if (source == null) { return; }
		if (entity == null) { entity = new TemplateContentENT(); }
		if (TemplateContent.class.isAssignableFrom(source.getClass())) {
			getEntity().clone((TemplateContent)source);
		}
	}


	
	@Override
	public ServiceList<TemplateBinding> getBindings() { return templateBindingService.listForTemplate(getTemplate()); }
	
	
}
