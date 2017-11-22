package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ServiceENT;

public interface K2EntityDAO extends GenericDAO<K2EntityENT, Long> {

	K2EntityENT fetchForName(String name);

	List<K2EntityENT> listForService(K2ServiceENT entity);

	List<K2EntityENT> listSubEntitiesForEntity(K2EntityENT entity);

	List<K2EntityENT> listRootEntitiesForService(K2ServiceENT entity);

}
