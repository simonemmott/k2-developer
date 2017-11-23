package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2TypeDefENT;

public interface K2TypeDefDAO extends GenericDAO<K2TypeDefENT, Long> {

	List<K2TypeDefENT> listForEntity(K2EntityENT entity);

}
