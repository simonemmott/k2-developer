package com.k2.common.sequences;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
//import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import com.k2.common.identity.ID;


public class DomainedSequenceIdentifier implements IdentifierGenerator, Configurable {
	
	public static final String PARM_SEQUENCE_NAME = "sequenceName";
	
	private String sequenceName = "defaultSequence";

	public DomainedSequenceIdentifier() {}

	@Autowired(required=true)
	private Sequences seqs;
	public DomainedSequenceIdentifier setSequences(Sequences seqs) {
		this.seqs = seqs;
		return this;
	}

/* Hibernate 5.2.10
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		sequenceName = ConfigurationHelper.getString(PARM_SEQUENCE_NAME, params, sequenceName);
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object entity) throws HibernateException {
		if (entity instanceof ID) {
			ID<?> identifiable = (ID<?>) entity;
			Long id = identifiable.getID();
			if (id != null) { return id; }
		}
		if (seqs == null) { throw new SequenceError("Unable to generate domained sequences with a null sequnces service!"); }
		return seqs.getNextValue(sequenceName);
	}
*/
/* Hibernate 4.3.11 */
	@Override
	public void configure(Type type, Properties params, Dialect dialect) throws MappingException {
		sequenceName = ConfigurationHelper.getString(PARM_SEQUENCE_NAME, params, sequenceName);
	}

	@Override
	public Serializable generate(SessionImplementor session, Object entity) throws HibernateException {
		if (entity instanceof ID) {
			ID identifiable = (ID) entity;
			Long id = identifiable.getID();
			if (id != null) { return id; }
		}
		if (seqs == null) { throw new SequenceError("Unable to generate domained sequences with a null sequences service!"); }
		return seqs.getNextValue(sequenceName);
	}

}
