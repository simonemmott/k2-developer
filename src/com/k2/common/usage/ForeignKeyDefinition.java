package com.k2.common.usage;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import com.k2.common.service.EntityService;
import com.k2.common.util.ClassUtil;
import com.k2.common.writeEvents.ValidationException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ForeignKeyDefinition<U,E>  extends ApplicationObjectSupport implements ApplicationContextAware{
	
	public static enum Cascade{
		FAIL, DELETE, BLANK, NONE
	}
	
	private Class<U> usedEntityClass;
	private Class<E> linkedEntityClass;
	private String field;
	private Cascade cascade;
	private Serializable usageKey;
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	@Autowired(required=true)
	private EntityMethodCache entityMethodCache;
	
	public ForeignKeyDefinition(Class<U> usedEntityClass, Class<E> linkedEntityClass, String field, Cascade cascade) {
		this.usedEntityClass = usedEntityClass;
		this.linkedEntityClass = linkedEntityClass;
		this.field = field;
		this.cascade = cascade;
		
	}
	
	public ForeignKeyDefinition<U,E> setUsageKey(Serializable usageKey) { this.usageKey = usageKey; return this; }
	
	public Class<E> getLinkedEntityClass() { return linkedEntityClass; }
	public Cascade getCascade() { return cascade; }
	public List<E> getUsage() {
		Session sess = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = ((EntityManagerFactory)sessionFactory).getCriteriaBuilder();
		CriteriaQuery<E> criteria = builder.createQuery(linkedEntityClass);
		Root<E> root = criteria.from(linkedEntityClass);
		ParameterExpression<Serializable> parm = builder.parameter(Serializable.class);
		criteria.select(root).where(builder.equal(root.get(field), parm));
		TypedQuery<E> query = ((EntityManager)sess).createQuery(criteria);
		query.setParameter(parm, usageKey);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public void cascade() throws CascadeDeletionException, ValidationException {
		switch (cascade) {
		case BLANK:
			for (E linkedEntity : getUsage()) {
				Method setter = entityMethodCache.getSetterMethod(linkedEntityClass, field);
				try {
					setter.invoke(linkedEntity, (Object) null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					throw new CascadeDeleteError("Unable to invoke the setter method: "+setter.getName()+" on entity: "+ClassUtil.getEntityName(linkedEntityClass)+" to blank the link on cascade deletion"); 
				}
				
				((EntityService<E,?,?>) this.getApplicationContext().getBean(ClassUtil.getEntityName(linkedEntityClass)+"Service"))
						.getBO(linkedEntity)
						.write();
			}
			break;
		case DELETE:
			for (E linkedEntity : getUsage()) {
				((EntityService<E,?,?>) this.getApplicationContext().getBean(ClassUtil.getEntityName(linkedEntityClass)+"Service"))
				.getBO(linkedEntity)
				.delete();
			}
			break;
		case FAIL:
			if (!getUsage().isEmpty()) {
				String usedEntityName = ClassUtil.getEntityName(usedEntityClass);
				String linkedEntityName = ClassUtil.getEntityName(linkedEntityClass);
				throw new CascadeDeletionException("Unable to delete entity: "+usedEntityName+" with id: "+usageKey+" as there are instances of the entity: "+linkedEntityName+" that link to it via the field: "+field);
			}
			break;
		case NONE:
			break;
		default:
			break;
		
		}
	}

}
