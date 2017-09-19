package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.dev.model.Template;
import com.k2.dev.model.entity.TemplateENT;

public interface TemplateService extends EntityService<TemplateENT, Long, Template> {

	public Template newTemplate();
	public Template fetchTemplate(Long id);
	public Template fetchForName(String name);

}
