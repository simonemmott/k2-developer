package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.DependencyENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2MethodENT;

public interface K2MethodDAO extends GenericDAO<K2MethodENT, Long> {

	List<K2MethodENT> listForEntity(K2EntityENT entity);

}
