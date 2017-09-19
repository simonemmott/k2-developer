package com.k2.dev.dao;

import java.util.List;

import com.k2.dev.model.entity.TemplateENT;
import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.TemplateContentENT;

public interface TemplateContentDAO extends GenericDAO<TemplateContentENT, Long> {

	public List<TemplateContentENT> listForTemplate(TemplateENT template);

	public List<TemplateContentENT> listForTemplateContent(TemplateContentENT entity);

}
