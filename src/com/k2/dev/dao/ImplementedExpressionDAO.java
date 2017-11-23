package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.BoundExpressionParameterENT;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ServiceENT;

public interface ImplementedExpressionDAO extends GenericDAO<ImplementedExpressionENT, Long> {

	List<ImplementedExpressionENT> listForServiceOnly(K2ServiceENT entity);

	List<ImplementedExpressionENT> listForServiceAndEntity(K2ServiceENT entity, K2EntityENT entity2);

	List<ImplementedExpressionENT> listForService(K2ServiceENT entity);

	List<ImplementedExpressionENT> listForServiceAndBoundToParameter(K2ServiceENT entity, BoundExpressionParameterENT entity2);

}
