package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.ExpressionParameterDAO;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.ExpressionParameterENT;

@Repository("ExpressionParameterDAO")
@Transactional
public class ExpressionParameterHibernateDAO extends HibernateDAO<ExpressionParameterENT, Long> implements ExpressionParameterDAO {
	
	@Autowired
	CriteriaBuilderFactory builderFactory;

	@Override
	protected Class<? extends ExpressionParameterENT> getDaoType() { return ExpressionParameterENT.class; }

	@Override
	public List<ExpressionParameterENT> ListForExpression(ExpressionENT expression) {
		
		CriteriaBuilder<ExpressionParameterENT> query = builderFactory.create(getEM(), ExpressionParameterENT.class, "root")
				.where("root.expression").eqExpression(":Expression")
				.setParameter("Expression", expression);
		
		return query.getResultList();

		
		
		
/*		
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<ExpressionParameterENT> criteria = builder.createQuery(ExpressionParameterENT.class);
		Root<ExpressionParameterENT> root = criteria.from(ExpressionParameterENT.class);
		ParameterExpression<ExpressionENT> parm = builder.parameter(ExpressionENT.class);
		criteria.select(root).where(builder.equal(root.get("expression"), parm));
		TypedQuery<ExpressionParameterENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, entity);
		return query.getResultList();
*/
	}

	@Override
	public List<ExpressionParameterENT> ListForExpressionOrBoundParameterExpression(ExpressionENT expression, ExpressionENT boundParameterExpression) {
		CriteriaBuilder<ExpressionParameterENT> query = builderFactory.create(getEM(), ExpressionParameterENT.class, "root")
				.whereOr()
					.whereAnd()
						.where(":BoundParameterExpression").isNull()
						.where("root.expression").eqExpression(":Expression")
					.endAnd()
					.whereAnd()
						.where(":BoundParameterExpression").isNotNull()
						.where("root.expression").eqExpression(":BoundParameterExpression")
					.endAnd()
				.endOr()
				.setParameter("Expression", expression)
				.setParameter("BoundParameterExpression", boundParameterExpression);
		
		return query.getResultList();
	}


}
