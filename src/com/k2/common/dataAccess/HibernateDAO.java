package com.k2.common.dataAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

 
/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * @see SessionFactory
 */
public class HibernateDAO<E, K extends Serializable> implements GenericDAO<E, K> {
 
    protected Class<? extends E> daoType;
    
    @PersistenceUnit
    protected EntityManagerFactory emf;
    
    @PersistenceContext
    protected EntityManager em;
    
    @Override
	public EntityManagerFactory getEMF() { return emf; }
    @Override
	public EntityManager getEM() { return em; }
    
    private SessionFactory sessionFactory;
    protected SessionFactory getSessionFactory() {
    	if (sessionFactory == null) { sessionFactory = emf.unwrap(SessionFactory.class); }
    	return sessionFactory;
    }
    
    protected Session getCurrentSession() {
    	if (sessionFactory == null) { sessionFactory = emf.unwrap(SessionFactory.class); }
    	return sessionFactory.getCurrentSession();
//    	if (!sess.equals(em.unwrap(Session.class))) { throw new DAOError("The entity manager and current session are not the same!"); }
//    	return em.unwrap(Session.class);
    }
    
/*    
    public HibernateDAO<E, K> setSessionFactory(SessionFactory sf) {
    	if (sf == null) { throw new DAOError("The entity manager factory cannot be set to null on the hibernate DAO"); }
    	this.sf = sf;
    	return this;
    }
    public SessionFactory getSessionFactory() {
		return sf;
    }
*/ 

    
    @SuppressWarnings("unchecked")
	public HibernateDAO() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0].getClass();
    }
 
    @Override
    @Transactional
    public void add(E entity) {
    	em.persist(entity);
//    	Session sess = getCurrentSession();
//    	sess.save(entity);
    }
 
    @Override
    @Transactional
    public void update(E entity) {
    	entity = em.merge(entity);
//    	Session sess = getCurrentSession();
//    	sess.saveOrUpdate(entity);
    }
 
    @Override
    @Transactional
    public void remove(E entity) {
    	em.remove(entity);
//    	Session sess = getCurrentSession();
//    	sess.delete(entity);
    }
 
    @Override
    @Transactional
    public E fetch(K key) {
    	return em.find(daoType, key);
//    	Session sess = getCurrentSession();
//        return (E) sess.get(daoType, key);
    }
 
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<E> list() {
//    	Session sess = getCurrentSession();   	
		CriteriaQuery<?> criteria = emf.getCriteriaBuilder().createQuery(daoType);
		TypedQuery<?> query = em.createQuery(criteria);
    	return (List<E>) query.getResultList();
    }
}