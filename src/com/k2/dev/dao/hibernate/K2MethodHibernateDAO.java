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
import com.k2.dev.dao.K2MethodDAO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2MethodENT;

@Repository("k2MethodDAO")
@Transactional
public class K2MethodHibernateDAO extends HibernateDAO<K2MethodENT, Long> implements K2MethodDAO {

	@Override
	protected Class<? extends K2MethodENT> getDaoType() { return K2MethodENT.class; }

	@Override
	public List<K2MethodENT> listForEntity(K2EntityENT entity) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2MethodENT> criteria = builder.createQuery(K2MethodENT.class);
		Root<K2MethodENT> root = criteria.from(K2MethodENT.class);
		ParameterExpression<K2EntityENT> parm = builder.parameter(K2EntityENT.class);
		criteria.select(root).where(builder.equal(root.get("k2Entity"), parm));
		TypedQuery<K2MethodENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, entity);
		return query.getResultList();
	}

}
