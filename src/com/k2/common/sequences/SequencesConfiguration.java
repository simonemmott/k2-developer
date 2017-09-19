package com.k2.common.sequences;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.k2.common.parameters.SequencesPrarameters;
import com.k2.common.spring.ApplicationError;


@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource("file:${K2_HOME}/conf/k2.properties")
public class SequencesConfiguration {
	
	@Bean(name="sequencesInitialised")
	public boolean sequencesInitialised() {
		initialiseSequences();
		return true;
	}
	
	@Autowired
	Environment env;

	@Bean
	public SequencesConfig getSequenceConfig() {
		String seqDefClassName = env.getProperty(SequencesPrarameters.DEFINITION);
		if ((seqDefClassName==null) || ("".equals(seqDefClassName))) {
			return new EmptySequencesConfig();
		}
		try {
			Class<?> seqDef = Class.forName(seqDefClassName);
			if (SequencesConfig.class.isAssignableFrom(seqDef)) {
				try {
					return (SequencesConfig) seqDef.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					System.err.println("Unable to instantiate sequence defintion class '"+seqDefClassName+"', defaulting to EmptySequenceConfig");
					e.printStackTrace();
					return new EmptySequencesConfig();
				}
			} else {
				System.err.println("Sequence defintion class '"+seqDefClassName+"' does not implement SequencesConfig, defaulting to EmptySequenceConfig");
				return new EmptySequencesConfig();
			}
		} catch (ClassNotFoundException e) {
			System.err.println("No sequence defintion class found for '"+seqDefClassName+"', defaulting to EmptySequenceConfig");
			e.printStackTrace();
			return new EmptySequencesConfig();
		}
	}
	
	@Autowired(required=true)
	protected EntityManagerFactory emf;
	@Autowired(required=true)
	SequenceDAO sequenceDao;
	
	private Sequences seqs;
	@Bean
	public Sequences sequences() {
		seqs = SequencesFactory.create();
		return seqs;
	}
	
	private Sequences initialiseSequences() {
		seqs = sequences();
		seqs.setEntityManagerFactory(emf);
		if (sequenceDao == null) { throw new ApplicationError("Hmmm!"); }
		seqs.setDAO(sequenceDao);
		seqs.setIdentityDomain(env.getProperty(SequencesPrarameters.DOMAIN, Long.class, 0L));
		return seqs;
	}
	
//	@Bean
//	public SequenceDAO getSequenceDAO() {
//		SequenceDAO dao = new SequenceHibernateDAO();
//		return dao;
//	}

}
