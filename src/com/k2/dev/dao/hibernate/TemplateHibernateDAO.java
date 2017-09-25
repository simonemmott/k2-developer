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
import com.k2.dev.dao.TemplateDAO;
import com.k2.dev.model.entity.TemplateENT;

@Repository("templateDAO")
@Transactional
public class TemplateHibernateDAO extends HibernateDAO<TemplateENT, Long> implements TemplateDAO {

	@Override
	public TemplateENT fetchForName(String name) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<TemplateENT> criteria = builder.createQuery(TemplateENT.class);
		Root<TemplateENT> root = criteria.from(TemplateENT.class);
		ParameterExpression<String> nameParm = builder.parameter(String.class);
		criteria.select(root).where(builder.equal(root.get("name"), nameParm));
		TypedQuery<TemplateENT> query = em.createQuery(criteria);
		query.setParameter(nameParm, name);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	protected Class<? extends TemplateENT> getDaoType() { return TemplateENT.class; }

}
