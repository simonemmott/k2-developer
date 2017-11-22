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
import com.k2.dev.dao.K2TypeFieldDAO;
import com.k2.dev.model.entity.K2TypeFieldENT;

@Repository("k2TypeFieldDAO")
@Transactional
public class K2TypeFieldHibernateDAO extends HibernateDAO<K2TypeFieldENT, Long> implements K2TypeFieldDAO {

	@Override
	protected Class<? extends K2TypeFieldENT> getDaoType() { return K2TypeFieldENT.class; }

}