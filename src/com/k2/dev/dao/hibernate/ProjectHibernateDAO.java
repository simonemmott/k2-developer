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
import com.k2.dev.dao.ProjectDAO;
import com.k2.dev.model.entity.ProjectENT;

@Repository("projectDAO")
@Transactional
public class ProjectHibernateDAO extends HibernateDAO<ProjectENT, Long> implements ProjectDAO {

	@Override
	public ProjectENT fetchForName(String name) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<ProjectENT> criteria = builder.createQuery(ProjectENT.class);
		Root<ProjectENT> root = criteria.from(ProjectENT.class);
		ParameterExpression<String> nameParm = builder.parameter(String.class);
		criteria.select(root).where(builder.equal(root.get("name"), nameParm));
		TypedQuery<ProjectENT> query = getEM().createQuery(criteria);
		query.setParameter(nameParm, name);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	protected Class<? extends ProjectENT> getDaoType() { return ProjectENT.class; }

}
