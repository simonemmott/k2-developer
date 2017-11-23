package com.k2.dev.dao.hibernate;


import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.TemplateBindingDAO;
import com.k2.dev.model.entity.TemplateENT;
import com.k2.dev.model.entity.TemplateBindingENT;

@Repository("templateBindingDAO")
@Transactional
public class TemplateBindingHibernateDAO extends HibernateDAO<TemplateBindingENT, Long> implements TemplateBindingDAO {

	@Override
	public List<TemplateBindingENT> listForTemplate(TemplateENT template) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<TemplateBindingENT> criteria = builder.createQuery(TemplateBindingENT.class);
		Root<TemplateBindingENT> root = criteria.from(TemplateBindingENT.class);
		ParameterExpression<TemplateENT> parm = builder.parameter(TemplateENT.class);
		criteria.select(root).where(builder.equal(root.get("snippet"), parm));
		TypedQuery<TemplateBindingENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, template);
		return query.getResultList();
	}

	@Override
	protected Class<? extends TemplateBindingENT> getDaoType() { return TemplateBindingENT.class; }


}
