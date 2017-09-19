package com.k2.common.sequences;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.k2.common.dataAccess.HibernateDAO;

@Repository
@Transactional
public class SequenceHibernateDAO extends HibernateDAO<SequenceENT, Long> implements SequenceDAO {
	
	@Override
	public List<SequenceENT> getDomainSequences(Long domainId) {

		Session sess = null;
		List<SequenceENT> results;
		try {
			sess = getSessionFactory().openSession();
		
//			results = sess.createQuery("Select s From SequenceImpl s Where s.id.identityDomain = :domain", SequenceImpl.class)
//					.setParameter("domain", domainId)
//					.getResultList();
		
		
		
			CriteriaBuilder builder = emf.getCriteriaBuilder();
			CriteriaQuery<SequenceENT> criteria = builder.createQuery(SequenceENT.class);
			Root<SequenceENT> root = criteria.from(SequenceENT.class);
			ParameterExpression<Long> parm = builder.parameter(Long.class);
			criteria.select(root).where(builder.equal(root.get("identityDomain"), parm));
			TypedQuery<SequenceENT> query = em.createQuery(criteria);
			query.setParameter(parm, domainId);
			results = query.getResultList();

		} finally {
			if (sess != null) {
//				if (sess.getTransaction()!=null) { sess.getTransaction().rollback(); }
				if (sess.isOpen()) { sess.close(); }
			}
		}
		return results;

	}

}
