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
import com.k2.dev.dao.ImplementedExpressionDAO;
import com.k2.dev.dao.K2EntityDAO;
import com.k2.dev.dao.NativeExpressionDAO;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.NativeExpressionENT;

@Repository("NativeExpressionDAO")
@Transactional
public class NativeExpressionHibernateDAO extends HibernateDAO<NativeExpressionENT, Long> implements NativeExpressionDAO {

	@Override
	protected Class<? extends NativeExpressionENT> getDaoType() { return NativeExpressionENT.class; }

}