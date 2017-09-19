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
import com.k2.dev.dao.TemplateContentDAO;
import com.k2.dev.model.entity.TemplateENT;
import com.k2.dev.model.entity.TemplateContentENT;

@Repository("templateContentDAO")
@Transactional
public class TemplateContentHibernateDAO extends HibernateDAO<TemplateContentENT, Long> implements TemplateContentDAO {

	@Override
	public List<TemplateContentENT> listForTemplate(TemplateENT template) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();

		CriteriaQuery<TemplateContentENT> criteria = builder.createQuery(TemplateContentENT.class);
		Root<TemplateContentENT> root = criteria.from(TemplateContentENT.class);
		ParameterExpression<TemplateENT> parm = builder.parameter(TemplateENT.class);
		criteria.select(root).where(builder.equal(root.get("template"), parm));
		TypedQuery<TemplateContentENT> query = em.createQuery(criteria);
		query.setParameter(parm, template);
		return query.getResultList();
	}

	@Override
	public List<TemplateContentENT> listForTemplateContent(TemplateContentENT templateContent) {

		CriteriaBuilder builder = emf.getCriteriaBuilder();

		CriteriaQuery<TemplateContentENT> criteria = builder.createQuery(TemplateContentENT.class);
		Root<TemplateContentENT> root = criteria.from(TemplateContentENT.class);
		ParameterExpression<TemplateContentENT> parm = builder.parameter(TemplateContentENT.class);
		criteria.select(root).where(builder.equal(root.get("parentContent"), parm));
		TypedQuery<TemplateContentENT> query = em.createQuery(criteria);
		query.setParameter(parm, templateContent);
		return query.getResultList();
	}

}
