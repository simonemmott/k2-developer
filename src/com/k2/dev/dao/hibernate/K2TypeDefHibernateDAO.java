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
import com.k2.dev.dao.K2TypeDefDAO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2TypeDefENT;

@Repository("k2TypeDefDAO")
@Transactional
public class K2TypeDefHibernateDAO extends HibernateDAO<K2TypeDefENT, Long> implements K2TypeDefDAO {
	
	@Override
	protected Class<? extends K2TypeDefENT> getDaoType() { return K2TypeDefENT.class; }

	@Override
	public List<K2TypeDefENT> listForEntity(K2EntityENT entity) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2TypeDefENT> criteria = builder.createQuery(K2TypeDefENT.class);
		Root<K2TypeDefENT> root = criteria.from(K2TypeDefENT.class);
		ParameterExpression<K2EntityENT> parm = builder.parameter(K2EntityENT.class);
		criteria.select(root).where(builder.equal(root.get("k2Entity"), parm));
		TypedQuery<K2TypeDefENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, entity);
		return query.getResultList();
	}


}
