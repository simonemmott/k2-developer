package com.k2.dev.dao.hibernate;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.k2.common.dataAccess.HibernateDAO;
import com.k2.dev.dao.K2EntityDAO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2ServiceENT;

@Repository("k2EntityDAO")
@Transactional
public class K2EntityHibernateDAO extends HibernateDAO<K2EntityENT, Long> implements K2EntityDAO {

	@Autowired
	CriteriaBuilderFactory builderFactory;

	@Override
	protected Class<? extends K2EntityENT> getDaoType() { return K2EntityENT.class; }

	@Override
	public K2EntityENT fetchForName(String name) {

		CriteriaBuilder<K2EntityENT> query = builderFactory.create(getEM(), K2EntityENT.class, "root")
				.where("root.name").eqExpression(":Name")
				.setParameter("Name", name);
		
		return query.getSingleResult();

/*				
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2EntityENT> criteria = builder.createQuery(K2EntityENT.class);
		Root<K2EntityENT> root = criteria.from(K2EntityENT.class);
		ParameterExpression<String> nameParm = builder.parameter(String.class);
		criteria.select(root).where(builder.equal(root.get("name"), nameParm));
		TypedQuery<K2EntityENT> query = getEM().createQuery(criteria);
		query.setParameter(nameParm, name);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
*/
	}

	@Override
	public List<K2EntityENT> listForService(K2ServiceENT k2Service) {
		CriteriaBuilder<K2EntityENT> query = builderFactory.create(getEM(), K2EntityENT.class, "root")
				.where("root.k2Service").eqExpression(":k2Service")
				.setParameter("k2Service", k2Service);
		
		return query.getResultList();

/*		
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2EntityENT> criteria = builder.createQuery(K2EntityENT.class);
		Root<K2EntityENT> root = criteria.from(K2EntityENT.class);
		ParameterExpression<K2ServiceENT> k2ServiceParm = builder.parameter(K2ServiceENT.class);
		criteria.select(root).where(
			builder.equal(root.get("k2Service"), k2ServiceParm)
		);
		TypedQuery<K2EntityENT> query = getEM().createQuery(criteria);
		query.setParameter(k2ServiceParm, k2Service);
		return query.getResultList();
*/
	}

	@Override
	public List<K2EntityENT> listSubEntitiesForEntity(K2EntityENT k2entity) {
		CriteriaBuilder<K2EntityENT> query = builderFactory.create(getEM(), K2EntityENT.class, "root")
				.where("root.extendsEntity").eqExpression(":k2Entity")
				.setParameter("k2Entity", k2entity);
		
		return query.getResultList();

/*		
		CriteriaBuilder builder = emf.getCriteriaBuilder();
		
		CriteriaQuery<K2EntityENT> criteria = builder.createQuery(K2EntityENT.class);
		Root<K2EntityENT> root = criteria.from(K2EntityENT.class);
		ParameterExpression<K2EntityENT> k2ExtendsEntityParm = builder.parameter(K2EntityENT.class);
		criteria.select(root).where(
			builder.equal(root.get("extendsEntity"), k2ExtendsEntityParm)
		);
		TypedQuery<K2EntityENT> query = getEM().createQuery(criteria);
		query.setParameter(k2ExtendsEntityParm, k2entity);
		return query.getResultList();
*/
	}

	@Override
	public List<K2EntityENT> listRootEntitiesForService(K2ServiceENT k2Service) {
		CriteriaBuilder<K2EntityENT> query = builderFactory.create(getEM(), K2EntityENT.class, "root")
				.where("root.k2Service").eqExpression(":k2Service")
				.where("root.extendsEntity").isNull()
				.setParameter("k2Service", k2Service);
		
		return query.getResultList();
	}

}