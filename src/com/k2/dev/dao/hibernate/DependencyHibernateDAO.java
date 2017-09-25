package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.DependencyDAO;
import com.k2.dev.model.entity.DependencyENT;

@Repository("dependencyDAO")
@Transactional
public class DependencyHibernateDAO extends HibernateDAO<DependencyENT, Long> implements DependencyDAO {

	@Override
	protected Class<? extends DependencyENT> getDaoType() { return DependencyENT.class; }

}
