package com.k2.dev.dao;

import java.util.List;

import com.k2.dev.model.entity.TemplateENT;
import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.TemplateBindingENT;

public interface TemplateBindingDAO extends GenericDAO<TemplateBindingENT, Long> {

	public List<TemplateBindingENT> listForTemplate(TemplateENT template);

}
