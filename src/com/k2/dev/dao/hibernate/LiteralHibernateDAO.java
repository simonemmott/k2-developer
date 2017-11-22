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
import com.k2.dev.dao.LiteralDAO;
import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.entity.LiteralENT;

@Repository("literalDAO")
@Transactional
public class LiteralHibernateDAO extends HibernateDAO<LiteralENT, Long> implements LiteralDAO {

	@Override
	protected Class<? extends LiteralENT> getDaoType() { return LiteralENT.class; }

	@Override
	public List<LiteralENT> ListForService(K2ServiceENT k2Service) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<LiteralENT> criteria = builder.createQuery(LiteralENT.class);
		Root<LiteralENT> root = criteria.from(LiteralENT.class);
		ParameterExpression<K2ServiceENT> k2ServiceParm = builder.parameter(K2ServiceENT.class);
		criteria.select(root).where(
			builder.equal(root.get("k2Service"), k2ServiceParm)
		);
		TypedQuery<LiteralENT> query = getEM().createQuery(criteria);
		query.setParameter(k2ServiceParm, k2Service);
		return query.getResultList();
	}

}