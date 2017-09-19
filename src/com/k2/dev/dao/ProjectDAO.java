package com.k2.dev.dao;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.ProjectENT;

public interface ProjectDAO extends GenericDAO<ProjectENT, Long> {

	public ProjectENT fetchForName(String name);

}
