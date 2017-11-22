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
import com.k2.dev.dao.K2ListDAO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ListENT;

@Repository("k2ListDAO")
@Transactional
public class K2ListHibernateDAO extends HibernateDAO<K2ListENT, Long> implements K2ListDAO {
	
	@Override
	protected Class<? extends K2ListENT> getDaoType() { return K2ListENT.class; }

	@Override
	public List<K2ListENT> listForEntity(K2EntityENT entity) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2ListENT> criteria = builder.createQuery(K2ListENT.class);
		Root<K2ListENT> root = criteria.from(K2ListENT.class);
		ParameterExpression<K2EntityENT> parm = builder.parameter(K2EntityENT.class);
		criteria.select(root).where(builder.equal(root.get("k2Entity"), parm));
		TypedQuery<K2ListENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, entity);
		return query.getResultList();
	}


}
