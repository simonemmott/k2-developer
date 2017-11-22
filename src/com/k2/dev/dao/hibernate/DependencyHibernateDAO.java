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
import com.k2.dev.dao.DependencyDAO;
import com.k2.dev.model.entity.DependencyENT;
import com.k2.dev.model.entity.K2MethodENT;

@Repository("dependencyDAO")
@Transactional
public class DependencyHibernateDAO extends HibernateDAO<DependencyENT, Long> implements DependencyDAO {

	@Override
	protected Class<? extends DependencyENT> getDaoType() { return DependencyENT.class; }

	@Override
	public List<DependencyENT> listForMethod(K2MethodENT method) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<DependencyENT> criteria = builder.createQuery(DependencyENT.class);
		Root<DependencyENT> root = criteria.from(DependencyENT.class);
		ParameterExpression<K2MethodENT> parm = builder.parameter(K2MethodENT.class);
		criteria.select(root).where(builder.equal(root.get("k2Method"), parm));
		TypedQuery<DependencyENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, method);
		return query.getResultList();
	}

}
