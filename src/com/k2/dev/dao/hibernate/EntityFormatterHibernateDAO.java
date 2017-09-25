package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.EntityFormatterDAO;
import com.k2.dev.model.entity.EntityFormatterENT;

@Repository("entityFormatterDAO")
@Transactional
public class EntityFormatterHibernateDAO extends HibernateDAO<EntityFormatterENT, Long> implements EntityFormatterDAO {

	@Override
	protected Class<? extends EntityFormatterENT> getDaoType() { return EntityFormatterENT.class; }

}