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
import com.k2.dev.dao.K2FieldDAO;
import com.k2.dev.dao.K2NativeFieldDAO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2NativeFieldENT;

@Repository("k2NativeFieldDAO")
@Transactional
public class K2NativeFieldHibernateDAO extends HibernateDAO<K2NativeFieldENT, Long> implements K2NativeFieldDAO {

	@Override
	protected Class<? extends K2NativeFieldENT> getDaoType() { return K2NativeFieldENT.class; }

}