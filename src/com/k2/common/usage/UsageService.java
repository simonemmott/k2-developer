package com.k2.common.usage;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsageService extends ApplicationObjectSupport implements ApplicationContextAware{

	public UsageService() {}
	
	public EntityUsage<?> getUsage(Class<?> entityClass, Serializable key) {
		
		if (entityClass.isAnnotationPresent(Entity.class)) {
			String usageComponentName = entityClass.getAnnotation(Entity.class).name()+"Usage";
			ApplicationContext appContext = getApplicationContext();
			if (appContext.containsBean(usageComponentName)) {
				return ((EntityUsage<?>) appContext.getBean(usageComponentName))
						.setEntityClass(entityClass)
						.setEntityKey(key);
			}
		} 
		return EntityUsageFactory.unused();
		
	}
	
	

}
