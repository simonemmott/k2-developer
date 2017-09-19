package com.k2.dev.dao;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.TemplateENT;

public interface TemplateDAO extends GenericDAO<TemplateENT, Long> {

	public TemplateENT fetchForName(String name);

}
