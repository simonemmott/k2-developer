package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.entity.LiteralENT;

public interface LiteralDAO extends GenericDAO<LiteralENT, Long> {

	List<LiteralENT> ListForService(K2ServiceENT entity);

}
