package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.entity.TemplateContentENT;

public interface TemplateContentService extends EntityService<TemplateContentENT, Long, TemplateContent> {

	public TemplateContent newTemplateContent();
	public TemplateContent fetchTemplateContent(Long id);

	public ServiceList<TemplateContent> listForTemplate(Template template);

	public ServiceList<TemplateContent> listForContent(TemplateContent templateContent);

}
