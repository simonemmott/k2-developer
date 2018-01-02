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
import com.k2.dev.dao.ComponentDAO;
import com.k2.dev.dao.K2FieldSetDAO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2FieldSetENT;
import com.k2.dev.model.entity.K2ServiceENT;

@Repository("k2FieldSetDAO")
@Transactional
public class K2FieldSetHibernateDAO extends HibernateDAO<K2FieldSetENT, Long> implements K2FieldSetDAO {
	
	@Override
	protected Class<? extends K2FieldSetENT> getDaoType() { return K2FieldSetENT.class; }

	@Override
	public List<K2FieldSetENT> listForComponent(ComponentENT componnt) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2FieldSetENT> criteria = builder.createQuery(K2FieldSetENT.class);
		Root<K2FieldSetENT> root = criteria.from(K2FieldSetENT.class);
		ParameterExpression<ComponentENT> parm = builder.parameter(ComponentENT.class);
		criteria.select(root).where(builder.equal(root.get("component"), parm));
		TypedQuery<K2FieldSetENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, componnt);
		return query.getResultList();
	}


}
