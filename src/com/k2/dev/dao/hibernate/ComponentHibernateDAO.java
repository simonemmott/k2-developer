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
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2ServiceENT;

@Repository("componentDAO")
@Transactional
public class ComponentHibernateDAO extends HibernateDAO<ComponentENT, Long> implements ComponentDAO {
	
	@Override
	protected Class<? extends ComponentENT> getDaoType() { return ComponentENT.class; }

	@Override
	public List<ComponentENT> listForService(K2ServiceENT entity) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<ComponentENT> criteria = builder.createQuery(ComponentENT.class);
		Root<ComponentENT> root = criteria.from(ComponentENT.class);
		ParameterExpression<K2ServiceENT> parm = builder.parameter(K2ServiceENT.class);
		criteria.select(root).where(builder.equal(root.get("k2Service"), parm));
		TypedQuery<ComponentENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, entity);
		return query.getResultList();
	}


}
