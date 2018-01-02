package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.K2FieldDAO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;

@Repository("k2FieldDAO")
@Transactional
public class K2FieldHibernateDAO extends HibernateDAO<K2FieldENT, Long> implements K2FieldDAO {

	@Override
	public List<K2FieldENT> listForEntity(K2EntityENT entity) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2FieldENT> criteria = builder.createQuery(K2FieldENT.class);
		Root<K2FieldENT> root = criteria.from(K2FieldENT.class);
		ParameterExpression<K2EntityENT> parm = builder.parameter(K2EntityENT.class);
		criteria.select(root).where(builder.equal(root.get("k2Entity"), parm));
		TypedQuery<K2FieldENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, entity);
		return query.getResultList();
	}

	@Override
	protected Class<? extends K2FieldENT> getDaoType() { return K2FieldENT.class; }

	@Override
	public List<K2FieldENT> listForComponent(ComponentENT component) {
		
		List<K2FieldENT> list = new ArrayList<K2FieldENT>();

		K2EntityENT k2Entity = (K2EntityENT)component;
		while (k2Entity != null) {
			list.addAll(this.listForEntity(k2Entity));
			k2Entity = k2Entity.getExtendsEntity();
		}

		return list;
	}

}