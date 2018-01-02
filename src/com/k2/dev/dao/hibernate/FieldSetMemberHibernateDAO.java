package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.ComponentDAO;
import com.k2.dev.dao.FieldSetMemberDAO;
import com.k2.dev.dao.K2FieldSetDAO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.FieldSetMemberENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2FieldSetENT;
import com.k2.dev.model.entity.K2ServiceENT;

@Repository("FieldSetMemberDAO")
@Transactional
public class FieldSetMemberHibernateDAO extends HibernateDAO<FieldSetMemberENT, Long> implements FieldSetMemberDAO {
	
	@Override
	protected Class<? extends FieldSetMemberENT> getDaoType() { return FieldSetMemberENT.class; }

	@Override
	public List<FieldSetMemberENT> listForFieldSet(K2FieldSetENT fieldSet) {
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<FieldSetMemberENT> criteria = builder.createQuery(FieldSetMemberENT.class);
		Root<FieldSetMemberENT> root = criteria.from(FieldSetMemberENT.class);
		ParameterExpression<K2FieldSetENT> parm = builder.parameter(K2FieldSetENT.class);
		criteria.select(root).where(builder.equal(root.get("fieldSet"), parm));
		TypedQuery<FieldSetMemberENT> query = getEM().createQuery(criteria);
		query.setParameter(parm, fieldSet);
		return query.getResultList();
	}

}
