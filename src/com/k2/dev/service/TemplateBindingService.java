package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.Template;
import com.k2.dev.model.entity.TemplateBindingENT;

public interface TemplateBindingService extends EntityService<TemplateBindingENT, Long, TemplateBinding> {

	public TemplateBinding newTemplateBinding();
	public TemplateBinding fetchTemplateBinding(Long id);

	public ServiceList<TemplateBinding> listForTemplate(Template template);
	
}
