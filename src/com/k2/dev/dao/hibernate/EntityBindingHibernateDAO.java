package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.EntityBindingDAO;
import com.k2.dev.model.entity.EntityBindingENT;

@Repository("entityBindingDAO")
@Transactional
public class EntityBindingHibernateDAO extends HibernateDAO<EntityBindingENT, Long> implements EntityBindingDAO {

}