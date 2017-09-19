package com.k2.dev.dao;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.K2EntityENT;

public interface K2EntityDAO extends GenericDAO<K2EntityENT, Long> {

	K2EntityENT fetchForName(String name);

}
