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
import com.k2.dev.dao.K2PermittedContentDAO;
import com.k2.dev.model.entity.K2PermittedContentENT;
import com.k2.dev.model.entity.K2SnippetContainerENT;

@Repository("permittedContentDAO")
@Transactional
public class K2PermittedContentHibernateDAO extends HibernateDAO<K2PermittedContentENT, Long> implements K2PermittedContentDAO {

	@Override
	public List<K2PermittedContentENT> listForSnippetContainer(K2SnippetContainerENT snippetContainer) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2PermittedContentENT> criteria = builder.createQuery(K2PermittedContentENT.class);
		Root<K2PermittedContentENT> root = criteria.from(K2PermittedContentENT.class);
		ParameterExpression<K2SnippetContainerENT> parm = builder.parameter(K2SnippetContainerENT.class);
		criteria.select(root).where(builder.equal(root.get("container"), parm));
		TypedQuery<K2PermittedContentENT> query = em.createQuery(criteria);
		query.setParameter(parm, snippetContainer);
		return query.getResultList();
	}

	@Override
	public K2PermittedContentENT fetchForSnippetContainerAndName(K2SnippetContainerENT snippetContainer, String name) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2PermittedContentENT> criteria = builder.createQuery(K2PermittedContentENT.class);
		Root<K2PermittedContentENT> root = criteria.from(K2PermittedContentENT.class);
		ParameterExpression<K2SnippetContainerENT> snippetContainerParm = builder.parameter(K2SnippetContainerENT.class);
		ParameterExpression<String> nameParm = builder.parameter(String.class);
		criteria.select(root).where(builder.and(
				builder.equal(root.get("container"), snippetContainerParm),
				builder.equal(root.get("name"), nameParm)));
		TypedQuery<K2PermittedContentENT> query = em.createQuery(criteria);
		query.setParameter(snippetContainerParm, snippetContainer);
		query.setParameter(nameParm, name);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	protected Class<? extends K2PermittedContentENT> getDaoType() { return K2PermittedContentENT.class; }

}
