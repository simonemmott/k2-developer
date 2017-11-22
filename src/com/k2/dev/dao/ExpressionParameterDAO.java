package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.ExpressionParameterENT;

public interface ExpressionParameterDAO extends GenericDAO<ExpressionParameterENT, Long> {

	List<ExpressionParameterENT> ListForExpression(ExpressionENT expression);

	List<ExpressionParameterENT> ListForExpressionOrBoundParameterExpression(ExpressionENT expression, ExpressionENT boundParameterExpression);

}
