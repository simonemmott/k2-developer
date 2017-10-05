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
import com.k2.dev.dao.K2SnippetContainerDAO;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.entity.K2SnippetContainerENT;

@Repository("snippetContainerDAO")
@Transactional
public class K2SnippetContainerHibernateDAO extends HibernateDAO<K2SnippetContainerENT, Long> implements K2SnippetContainerDAO {

	@Override
	public List<K2SnippetContainerENT> listForSnippet(K2SnippetENT snippet) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();
				
		CriteriaQuery<K2SnippetContainerENT> criteria = builder.createQuery(K2SnippetContainerENT.class);
		Root<K2SnippetContainerENT> root = criteria.from(K2SnippetContainerENT.class);
		ParameterExpression<K2SnippetENT> parm = builder.parameter(K2SnippetENT.class);
		criteria.select(root).where(builder.equal(root.get("snippet"), parm));
		TypedQuery<K2SnippetContainerENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, snippet);
		return query.getResultList();
	}

	@Override
	public K2SnippetContainerENT fetchForSnippetAndAlias(K2SnippetENT snippet, String alias) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();

		CriteriaQuery<K2SnippetContainerENT> criteria = builder.createQuery(K2SnippetContainerENT.class);
		Root<K2SnippetContainerENT> root = criteria.from(K2SnippetContainerENT.class);
		ParameterExpression<K2SnippetENT> snippetParm = builder.parameter(K2SnippetENT.class);
		ParameterExpression<String> aliasParm = builder.parameter(String.class);
		criteria.select(root).where(builder.and(
				builder.equal(root.get("snippet"), snippetParm),
				builder.equal(root.get("alias"), aliasParm)));
		TypedQuery<K2SnippetContainerENT> query = getEM().createQuery(criteria);
		query.setParameter(snippetParm, snippet);
		query.setParameter(aliasParm, alias);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	protected Class<? extends K2SnippetContainerENT> getDaoType() { return K2SnippetContainerENT.class; }

}
