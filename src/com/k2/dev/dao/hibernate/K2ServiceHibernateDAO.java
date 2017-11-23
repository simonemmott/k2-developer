package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.K2ServiceDAO;
import com.k2.dev.model.entity.K2ServiceENT;

@Repository("K2ServiceDAO")
@Transactional
public class K2ServiceHibernateDAO extends HibernateDAO<K2ServiceENT, Long> implements K2ServiceDAO {
	
	@Override
	protected Class<? extends K2ServiceENT> getDaoType() { return K2ServiceENT.class; }


}
