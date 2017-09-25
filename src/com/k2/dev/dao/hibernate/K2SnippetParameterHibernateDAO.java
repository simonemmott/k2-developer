package com.k2.dev.dao.hibernate;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.K2SnippetParameterDAO;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.entity.K2SnippetParameterENT;

@Repository("snippetParameterDAO")
@Transactional
public class K2SnippetParameterHibernateDAO extends HibernateDAO<K2SnippetParameterENT, Long> implements K2SnippetParameterDAO {

	@Override
	public List<K2SnippetParameterENT> listForSnippet(K2SnippetENT snippet) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2SnippetParameterENT> criteria = builder.createQuery(K2SnippetParameterENT.class);
		Root<K2SnippetParameterENT> root = criteria.from(K2SnippetParameterENT.class);
		ParameterExpression<K2SnippetENT> snippetParm = builder.parameter(K2SnippetENT.class);
		criteria.select(root).where(builder.equal(root.get("snippet"), snippetParm));
		TypedQuery<K2SnippetParameterENT> query = em.createQuery(criteria);
		query.setParameter(snippetParm, snippet);
		return query.getResultList();
	}

	@Override
	public K2SnippetParameterENT fetchForSnippetAndName(K2SnippetENT snippet, String name) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2SnippetParameterENT> criteria = builder.createQuery(K2SnippetParameterENT.class);
		Root<K2SnippetParameterENT> root = criteria.from(K2SnippetParameterENT.class);
		ParameterExpression<K2SnippetENT> snippetParm = builder.parameter(K2SnippetENT.class);
		ParameterExpression<String> nameParm = builder.parameter(String.class);
		criteria.select(root).where(builder.and(
				builder.equal(root.get("snippet"), snippetParm),
				builder.equal(root.get("name"), nameParm)));
		TypedQuery<K2SnippetParameterENT> query = em.createQuery(criteria);
		query.setParameter(snippetParm, snippet);
		query.setParameter(nameParm, name);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	protected Class<? extends K2SnippetParameterENT> getDaoType() { return K2SnippetParameterENT.class; }

}
