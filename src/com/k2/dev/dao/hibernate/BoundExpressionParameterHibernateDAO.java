package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.BoundExpressionParameterDAO;
import com.k2.dev.model.entity.BoundExpressionParameterENT;
import com.k2.dev.model.entity.ImplementedExpressionENT;

@Repository("BoundExpressionParameterDAO")
@Transactional
public class BoundExpressionParameterHibernateDAO extends HibernateDAO<BoundExpressionParameterENT, Long> implements BoundExpressionParameterDAO {
	
	@Override
	protected Class<? extends BoundExpressionParameterENT> getDaoType() { return BoundExpressionParameterENT.class; }

	@Override
	public List<BoundExpressionParameterENT> listForExpression(ImplementedExpressionENT implementedExpression) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<BoundExpressionParameterENT> criteria = builder.createQuery(BoundExpressionParameterENT.class);
		Root<BoundExpressionParameterENT> root = criteria.from(BoundExpressionParameterENT.class);
		ParameterExpression<ImplementedExpressionENT> implementedExpressionParm = builder.parameter(ImplementedExpressionENT.class);
		criteria.select(root).where(
			builder.equal(root.get("expression"), implementedExpressionParm)
		);
		TypedQuery<BoundExpressionParameterENT> query = getEM().createQuery(criteria);
		query.setParameter(implementedExpressionParm, implementedExpression);
		return query.getResultList();
	}


}
