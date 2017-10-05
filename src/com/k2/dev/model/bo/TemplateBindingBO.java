package com.k2.dev.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.service.ServiceModel;
import com.k2.common.meta.MetaEntity;
import com.k2.common.service.EntityService;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.Literal;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.entity.TemplateBindingENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.K2SnippetParameterService;
import com.k2.dev.service.K2SnippetService;
import com.k2.dev.service.LiteralService;
import com.k2.dev.service.TemplateBindingService;
import com.k2.dev.service.TemplateService;

@SuppressWarnings("rawtypes")
@Configurable
public class TemplateBindingBO extends K2SnippetBindingBO implements ServiceModel, TemplateBinding {
	
	@Autowired(required=true)
	protected TemplateBindingService service;
	@Override
	public EntityService getService() { return service; }
	
	@Autowired
	K2SnippetService snippetService;
	
	@Autowired
	TemplateService templateService;
	
	@Autowired
	K2SnippetParameterService snippetParameterService;
	
	@Autowired
	LiteralService literalService;
	
	public static TemplateBinding NULL = new TemplateBindingBO();
	public TemplateBindingBO() { super(null); }
    public TemplateBindingBO(PersistenceState state) { super(state); }
    public TemplateBindingBO(TemplateBindingENT entity, PersistenceState state) { super(state); this.entity = entity; }
    
	@Override
	public MetaEntity getMetaEntity() { return MetaModel.Entities.TEMPLATE_BINDING; }
		
	@Override
	public boolean isNull() { return (this == NULL); }
	@Override
	public TemplateBinding Null() { return NULL; }
	
	private TemplateBindingENT entity;
	@Override
	public TemplateBindingENT getEntity() { return entity; }
	
	@Override
	public Long getID() {if (isNull()) { return null; } return entity.getID(); }
	@Override
	public void setID(Long id) { if (isNull()) { return; } entity.setID(id); changed(); }
	
	@Override
	public K2Snippet getWidget() { if (isNull()) { return K2SnippetBO.NULL; } return snippetService.getBO(entity.getWidget()); }
	@Override
	public void setWidget(K2Snippet widget) { if (isNull()) { return; } entity.setWidget(widget.getEntity()); changed(); }

	@Override
	public K2SnippetParameter getBoundParameter() { if (isNull()) { return K2SnippetParameterBO.NULL; } return snippetParameterService.getBO(entity.getBoundParameter()); }
	@Override
	public void setBoundParameter(K2SnippetParameter boundParameter) { if (isNull()) { return; } entity.setBoundParameter(boundParameter.getEntity()); changed(); }

	@Override
	public TemplateBindingENT.Types.BindingSource getBindingSource() { if (isNull()) { return null; } return entity.getBindingSource(); }
	@Override
	public void setBindingSource(TemplateBindingENT.Types.BindingSource bindingSource) { if (isNull()) { return; } entity.setBindingSource(bindingSource); changed(); }

	@Override
	public Template getBindingTemplate() { if (isNull()) { return TemplateBO.NULL; } return templateService.getBO(entity.getBindingTemplate()); }
	@Override
	public void setBindingTemplate(Template template) { if (isNull()) { return; } entity.setBindingTemplate(template.getEntity()); changed(); }

	@Override
	public K2SnippetParameter getBindingParameter() { if (isNull()) { return K2SnippetParameterBO.NULL; } return snippetParameterService.getBO(entity.getBindingParameter()); }
	@Override
	public void setBindingParameter(K2SnippetParameter bindingParameter) { if (isNull()) { return; } entity.setBindingParameter(bindingParameter.getEntity()); changed(); }
	
	@Override
	public Literal getLiteral() { if (isNull()) { return LiteralBO.NULL; } return literalService.getBO(entity.getLiteral()); }
	@Override
	public void setLiteral(Literal literal) { if (isNull()) { return; } entity.setLiteral(literal.getEntity()); changed(); }
	

	
}
