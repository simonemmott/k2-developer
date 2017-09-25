package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.K2SnippetBindingDAO;
import com.k2.dev.model.entity.K2SnippetBindingENT;

@Repository("snippetBindingDAO")
@Transactional
public class K2SnippetBindingHibernateDAO extends HibernateDAO<K2SnippetBindingENT, Long> implements K2SnippetBindingDAO {

	@Override
	protected Class<? extends K2SnippetBindingENT> getDaoType() { return K2SnippetBindingENT.class; }

}
