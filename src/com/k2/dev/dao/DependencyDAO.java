package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.DependencyENT;
import com.k2.dev.model.entity.K2MethodENT;

public interface DependencyDAO extends GenericDAO<DependencyENT, Long> {

	List<DependencyENT> listForMethod(K2MethodENT method);

}
