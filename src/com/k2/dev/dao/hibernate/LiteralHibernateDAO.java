package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.LiteralDAO;
import com.k2.dev.model.entity.LiteralENT;

@Repository("literalDAO")
@Transactional
public class LiteralHibernateDAO extends HibernateDAO<LiteralENT, Long> implements LiteralDAO {

}