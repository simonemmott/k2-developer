package com.k2.common.dataAccess;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;
import org.springframework.util.StringUtils;

import com.k2.common.parameters.HibernateParameters;
import com.k2.common.parameters.JDBCParameters;
import com.k2.common.parameters.JPAParameters;
import com.k2.common.parameters.K2Parameters;
import com.k2.common.spring.ApplicationError;

@Configuration
@EnableTransactionManagement
@EnableSpringConfigured
@EnableAspectJAutoProxy
@EnableLoadTimeWeaving(aspectjWeaving=EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
@ComponentScan({"com.k2.common, com.k2.core, com.k2.dev"})
@PropertySource("file:${K2_HOME}/conf/k2.properties")
public class DataAccessConfiguration {
	
	//This is a comment
	
	@Autowired(required=true)
	private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource());
		emfb.setJpaVendorAdapter(hibernateAdapter());
		emfb.setJpaProperties(hibernateProperties());
		emfb.setPackagesToScan(StringUtils.split(env.getRequiredProperty(K2Parameters.K2_SCAN_ENTITIES), ":"));
		return emfb;
	}
	
	private Database getDatabase() {
		
		if (env == null) { throw new ApplicationError("Null environment when getting the database!"); }
		else { System.out.println(env.toString());}
		
		
		
		switch(env.getProperty(JPAParameters.DATABASE)) {
		case "DB2":
			return Database.DB2;
		case "DERBY":
			return Database.DERBY;
		case "H2":
			return Database.H2;
		case "HSQL":
			return Database.HSQL;
		case "INFORMIX":
			return Database.INFORMIX;
		case "MYSQL":
			return Database.MYSQL;
		case "ORACLE":
			return Database.ORACLE;
		case "POSTGRESQL":
			return Database.POSTGRESQL;
		case "SQLSERVER":
			return Database.SQL_SERVER;
		case "SYBASE":
			return Database.SYBASE;
		default:
			return Database.DEFAULT;
		}
	}
	
	private JpaVendorAdapter hibernateAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(getDatabase());
		adapter.setShowSql(env.getProperty(JPAParameters.SHOW_SQL, Boolean.class, false));
		adapter.setGenerateDdl(env.getProperty(JPAParameters.GENERATE_DDL, Boolean.class, false));
		if (env.containsProperty(JPAParameters.DATABASE_PLATFORM)) {
			adapter.setDatabasePlatform(env.getProperty(JPAParameters.DATABASE_PLATFORM));
		}
		return adapter;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		for (String propertyName : HibernateParameters.propertyList()) {
			if ((env.getProperty(propertyName) != null)&&(!"".equals(env.getProperty(propertyName)))) {
				properties.put(propertyName, env.getProperty(propertyName));
			}
		}
		return properties;
	}

	
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//		sessionFactory.setPackagesToScan(StringUtils.split(env.getRequiredProperty(K2Parameters.K2_SCAN_ENTITIES), ":"));
//		sessionFactory.setHibernateProperties(hibernateProperties());
//		return sessionFactory;
//	}
	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(JDBCParameters.JDBC_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(JDBCParameters.JDBC_URL));
		dataSource.setUsername(env.getRequiredProperty(JDBCParameters.JDBC_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(JDBCParameters.JDBC_PASSWORD));
		return dataSource;
	}
	
	@Bean
	public BeanPostProcessor persistenceTranslaction() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
//	@Bean
//	public JtaTransactionManager txManager() {
//		JtaTransactionManager txManager = new JtaTransactionManager();
//		return txManager;
//	}
	@Bean(name="transactionManager")
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(emf.unwrap(SessionFactory.class));
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	
	@Bean
	@Autowired
	public AnnotationTransactionAspect annotationTransactionAspect(PlatformTransactionManager txManager) {
		AnnotationTransactionAspect ata = new AnnotationTransactionAspect();
		ata.setTransactionManager(txManager);
		return ata;
	}
}
