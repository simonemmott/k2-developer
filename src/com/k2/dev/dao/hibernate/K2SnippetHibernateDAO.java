package com.k2.dev.dao.hibernate;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.K2SnippetDAO;
import com.k2.dev.model.entity.K2SnippetENT;

@Repository("snippetDAO")
@Transactional
public class K2SnippetHibernateDAO extends HibernateDAO<K2SnippetENT, Long> implements K2SnippetDAO {

	@Override
	public K2SnippetENT fetchForName(String name) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2SnippetENT> criteria = builder.createQuery(K2SnippetENT.class);
		Root<K2SnippetENT> root = criteria.from(K2SnippetENT.class);
		ParameterExpression<String> nameParm = builder.parameter(String.class);
		criteria.select(root).where(builder.equal(root.get("name"), nameParm));
		TypedQuery<K2SnippetENT> query = em.createQuery(criteria);
		query.setParameter(nameParm, name);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	protected Class<? extends K2SnippetENT> getDaoType() { return K2SnippetENT.class; }



}