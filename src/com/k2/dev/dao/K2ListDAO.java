package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ListENT;

public interface K2ListDAO extends GenericDAO<K2ListENT, Long> {

	List<K2ListENT> listForEntity(K2EntityENT entity);

}
