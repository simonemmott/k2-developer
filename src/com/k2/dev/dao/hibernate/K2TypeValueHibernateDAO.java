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
import com.k2.dev.dao.K2TypeValueDAO;
import com.k2.dev.model.entity.K2TypeDefENT;
import com.k2.dev.model.entity.K2TypeValueENT;

@Repository("k2TypeValueDAO")
@Transactional
public class K2TypeValueHibernateDAO extends HibernateDAO<K2TypeValueENT, Long> implements K2TypeValueDAO {
	
	@Override
	protected Class<? extends K2TypeValueENT> getDaoType() { return K2TypeValueENT.class; }

	@Override
	public List<K2TypeValueENT> ListForType(K2TypeDefENT typeDefinition) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2TypeValueENT> criteria = builder.createQuery(K2TypeValueENT.class);
		Root<K2TypeValueENT> root = criteria.from(K2TypeValueENT.class);
		ParameterExpression<K2TypeDefENT> parm = builder.parameter(K2TypeDefENT.class);
		criteria.select(root).where(builder.equal(root.get("typeDefinition"), parm));
		TypedQuery<K2TypeValueENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, typeDefinition);
		return query.getResultList();
	}


}
