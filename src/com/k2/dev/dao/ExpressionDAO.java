package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ServiceENT;

public interface ExpressionDAO extends GenericDAO<ExpressionENT, Long> {

	List<ExpressionENT> OfNativeOrServiceOrEntityExpressions(K2ServiceENT entity, K2EntityENT entity2);

}
