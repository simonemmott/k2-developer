package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.BoundExpressionParameterENT;
import com.k2.dev.model.entity.ImplementedExpressionENT;

public interface BoundExpressionParameterDAO extends GenericDAO<BoundExpressionParameterENT, Long> {

	List<BoundExpressionParameterENT> listForExpression(ImplementedExpressionENT entity);

}
