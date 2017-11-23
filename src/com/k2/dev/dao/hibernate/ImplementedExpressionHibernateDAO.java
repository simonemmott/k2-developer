package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.ImplementedExpressionDAO;
import com.k2.dev.dao.K2EntityDAO;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.entity.BoundExpressionParameterENT;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2ServiceENT;

@Repository("ImplementedExpressionDAO")
@Transactional
public class ImplementedExpressionHibernateDAO extends HibernateDAO<ImplementedExpressionENT, Long> implements ImplementedExpressionDAO {

	@Override
	protected Class<? extends ImplementedExpressionENT> getDaoType() { return ImplementedExpressionENT.class; }

	@Override
	public List<ImplementedExpressionENT> listForServiceOnly(K2ServiceENT k2Service) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<ImplementedExpressionENT> criteria = builder.createQuery(ImplementedExpressionENT.class);
		Root<ImplementedExpressionENT> root = criteria.from(ImplementedExpressionENT.class);
		ParameterExpression<K2ServiceENT> k2ServiceParm = builder.parameter(K2ServiceENT.class);
		criteria.select(root).where(
			builder.and(
				builder.equal(root.get("k2Service"), k2ServiceParm),
				builder.isNull(root.get("k2Entity"))
			)
		);
		TypedQuery<ImplementedExpressionENT> query = getEM().createQuery(criteria);
		query.setParameter(k2ServiceParm, k2Service);
		return query.getResultList();
	}

	@Override
	public List<ImplementedExpressionENT> listForServiceAndEntity(K2ServiceENT k2Service, K2EntityENT k2Entity) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<ImplementedExpressionENT> criteria = builder.createQuery(ImplementedExpressionENT.class);
		Root<ImplementedExpressionENT> root = criteria.from(ImplementedExpressionENT.class);
		ParameterExpression<K2ServiceENT> k2ServiceParm = builder.parameter(K2ServiceENT.class);
		ParameterExpression<K2EntityENT> k2EntityParm = builder.parameter(K2EntityENT.class);
		criteria.select(root).where(
			builder.and(
				builder.equal(root.get("k2Service"), k2ServiceParm),
				builder.equal(root.get("k2Entity"), k2EntityParm)
			)
		);
		TypedQuery<ImplementedExpressionENT> query = getEM().createQuery(criteria);
		query.setParameter(k2ServiceParm, k2Service);
		query.setParameter(k2EntityParm, k2Entity);
		return query.getResultList();
	}

	@Override
	public List<ImplementedExpressionENT> listForServiceAndBoundToParameter(K2ServiceENT k2Service, BoundExpressionParameterENT boundExpressionParameter) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<ImplementedExpressionENT> criteria = builder.createQuery(ImplementedExpressionENT.class);
		Root<ImplementedExpressionENT> root = criteria.from(ImplementedExpressionENT.class);
		ParameterExpression<K2ServiceENT> k2ServiceParm = builder.parameter(K2ServiceENT.class);
		ParameterExpression<BoundExpressionParameterENT> boundExpressionParameterParm = builder.parameter(BoundExpressionParameterENT.class);
		criteria.select(root).where(
			builder.and(
				builder.equal(root.get("k2Service"), k2ServiceParm),
				builder.equal(root.get("boundToParameter"), boundExpressionParameterParm)
			)
		);
		TypedQuery<ImplementedExpressionENT> query = getEM().createQuery(criteria);
		query.setParameter(k2ServiceParm, k2Service);
		query.setParameter(boundExpressionParameterParm, boundExpressionParameter);
		return query.getResultList();
	}

	@Override
	public List<ImplementedExpressionENT> listForService(K2ServiceENT k2Service) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<ImplementedExpressionENT> criteria = builder.createQuery(ImplementedExpressionENT.class);
		Root<ImplementedExpressionENT> root = criteria.from(ImplementedExpressionENT.class);
		ParameterExpression<K2ServiceENT> k2ServiceParm = builder.parameter(K2ServiceENT.class);
		criteria.select(root).where(
			builder.equal(root.get("k2Service"), k2ServiceParm)
		);
		TypedQuery<ImplementedExpressionENT> query = getEM().createQuery(criteria);
		query.setParameter(k2ServiceParm, k2Service);
		return query.getResultList();
	}

}