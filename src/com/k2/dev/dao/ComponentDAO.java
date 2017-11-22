package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2ServiceENT;

public interface ComponentDAO extends GenericDAO<ComponentENT, Long> {

	List<ComponentENT> listForService(K2ServiceENT entity);

}
