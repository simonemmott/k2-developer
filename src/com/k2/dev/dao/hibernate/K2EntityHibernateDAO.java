package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.K2EntityDAO;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.entity.K2EntityENT;

@Repository("k2EntityDAO")
@Transactional
public class K2EntityHibernateDAO extends HibernateDAO<K2EntityENT, Long> implements K2EntityDAO {

	@Override
	public K2EntityENT fetchForName(String name) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2EntityENT> criteria = builder.createQuery(K2EntityENT.class);
		Root<K2EntityENT> root = criteria.from(K2EntityENT.class);
		ParameterExpression<String> nameParm = builder.parameter(String.class);
		criteria.select(root).where(builder.equal(root.get("name"), nameParm));
		TypedQuery<K2EntityENT> query = getEM().createQuery(criteria);
		query.setParameter(nameParm, name);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	protected Class<? extends K2EntityENT> getDaoType() { return K2EntityENT.class; }

}