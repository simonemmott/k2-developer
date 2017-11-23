package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/* // JPA/Hibeernate only - Issue with treat causing inner joins in a where clause

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.ExpressionDAO;
//import com.k2.dev.dao.ExpressionDAO;
//import com.k2.dev.model.entity.ExpressionENT;
//import com.k2.dev.model.entity.ImplementedExpressionENT;
//import com.k2.dev.model.entity.K2EntityENT;
//import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.entity.ExpressionENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ServiceENT;

@Repository("ExpressionDAO")
@Transactional
public class ExpressionHibernateDAO extends HibernateDAO<ExpressionENT, Long> implements ExpressionDAO {
	
	@Autowired
	CriteriaBuilderFactory builderFactory;

	@Override
	protected Class<? extends ExpressionENT> getDaoType() { return ExpressionENT.class; }

	@Override
	public List<ExpressionENT> OfNativeOrServiceOrEntityExpressions(K2ServiceENT k2Service, K2EntityENT k2Entity) {
	
// JPA/Hibeernate/Blaze 
		CriteriaBuilder<ExpressionENT> query = builderFactory.create(getEM(), ExpressionENT.class, "root")
				.whereOr()
					.where("root.expressionType").eqExpression(":NATIVE")
					.whereAnd()
						.where("root.expressionType").eqExpression(":IMPLEMENTED")
						.where("TREAT(root AS ImplementedExpression).k2Service").eqExpression(":k2Service")
						.where("TREAT(root AS ImplementedExpression).k2Entity").isNull()
					.endAnd()
					.whereAnd()
						.where("root.expressionType").eqExpression(":IMPLEMENTED")
						.where("TREAT(root AS ImplementedExpression).k2Service").eqExpression(":k2Service")
						.where("TREAT(root AS ImplementedExpression).k2Entity").eqExpression(":k2Entity")
					.endAnd()
				.endOr()
				.setParameter("NATIVE", ExpressionENT.Types.ExpressionType.NATIVE)
				.setParameter("IMPLEMENTED", ExpressionENT.Types.ExpressionType.IMPLEMENTED)
				.setParameter("k2Service", k2Service)
				.setParameter("k2Entity", k2Entity);
		
		return query.getResultList();
		
/* // JPA/Hibeernate only - Issue with treat causing inner joins in a where clause
 		
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		CriteriaQuery<ExpressionENT> criteria = builder.createQuery(ExpressionENT.class);

		Root<ExpressionENT> expression = criteria.from(ExpressionENT.class);
		Root<ImplementedExpressionENT> implementedExpression = builder.treat(expression, ImplementedExpressionENT.class);
		
		ParameterExpression<K2ServiceENT> k2ServiceParm = builder.parameter(K2ServiceENT.class);
		ParameterExpression<K2EntityENT> k2EntityParm = builder.parameter(K2EntityENT.class);
		
//		criteria.select(expression).where(
//				builder.equal(expression.get("expressionType"), ExpressionENT.Types.ExpressionType.NATIVE)
//				);
		
		criteria.select(expression).where(
			builder.or(
				builder.equal(expression.get("expressionType"), ExpressionENT.Types.ExpressionType.NATIVE),
				builder.and(
						builder.equal(expression.get("expressionType"), ExpressionENT.Types.ExpressionType.IMPLEMENTED),
						builder.equal(implementedExpression.get("k2Service"), k2ServiceParm),
						builder.isNull(implementedExpression.get("k2Entity"))
				),
				builder.and(
						builder.equal(expression.get("expressionType"), ExpressionENT.Types.ExpressionType.IMPLEMENTED),
						builder.equal(implementedExpression.get("k2Service"), k2ServiceParm),
						builder.equal(implementedExpression.get("k2Entity"), k2EntityParm)
				)
			)
		);
		
		TypedQuery<ExpressionENT> query = getEM().createQuery(criteria);
		query.setParameter(k2ServiceParm, k2Service);
		query.setParameter(k2EntityParm, k2Entity);
		return query.getResultList();
*/
	}


}
