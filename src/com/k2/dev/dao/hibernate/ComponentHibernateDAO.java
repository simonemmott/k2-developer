package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.ComponentDAO;
import com.k2.dev.model.entity.ComponentENT;

@Repository("componentDAO")
@Transactional
public class ComponentHibernateDAO extends HibernateDAO<ComponentENT, Long> implements ComponentDAO {

}
