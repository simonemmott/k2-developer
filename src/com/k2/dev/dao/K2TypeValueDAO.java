package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2TypeDefENT;
import com.k2.dev.model.entity.K2TypeValueENT;

public interface K2TypeValueDAO extends GenericDAO<K2TypeValueENT, Long> {

	List<K2TypeValueENT> ListForType(K2TypeDefENT typeDefinition);

}
