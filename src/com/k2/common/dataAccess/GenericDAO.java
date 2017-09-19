package com.k2.common.dataAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.transaction.annotation.Transactional;

public interface GenericDAO<E, K> {
	/**
	 * Set the entity manager factory for this DAO
	 * @param emf	The entity manager factory to use with this DAO
	 * @return	This DAO
	 */
//    public GenericDAO<E, K> setSessionFactory(SessionFactory sf);
    /**
     * Get the entity manager factory used by this DAO
     * @return	The entity manager factory used by this DAO
     */
//    public SessionFactory getSessionFactory();
	
	
    public EntityManagerFactory getEMF();
    public EntityManager getEM();

	/**
	 * Create a new record in the datastore to store the entity
	 * @param entity	The entity to persist
	 */
	@Transactional
	void add(E entity);
	
	/**
	 * Update an exiting record in the datastore to store the entity
	 * @param entity	The entity to persist
	 */
	void update(E entity);
	
	/**
	 * Remove the entity from the datastore
	 * @param entity	The entity to remove
	 */
	void remove(E entity);
	
	/**
	 * Fetch the entity from the datastore identified by the key 
	 * @param key	The primary key of the record in the datastore
	 * @return		The entity matching the give key
	 */
	E fetch(K key);
	
	/**
	 * list all the entities of this type in the datastore
	 * @return	A list of all the entities in the datastore of this type
	 */
	List<E> list();
}
