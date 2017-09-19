package com.k2.common.sequences;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class SequencesImpl implements Sequences {
	
	Long identityDomain = new Long(0);
	Map<String, Sequence> sequences = new HashMap<String, Sequence>();
	
	private SequenceDAO dao;
	@Override
	public SequencesImpl setDAO(SequenceDAO dao) { this.dao = dao; return this; }

	private SessionFactory sf;
	@Override
	public Sequences setEntityManagerFactory(EntityManagerFactory emf) { 
		this.sf = emf.unwrap(SessionFactory.class); 
		return this; 
	}
	
	public SequencesImpl() {}

	@Override
	public Long getIdentityDomain() { return identityDomain; }
	@Override
	public Sequences setIdentityDomain(Long domain) {
		if (dao == null) { throw new SequenceError("Sequnces DAO cannot be null when setting the identity domain!"); }
		this.identityDomain = domain;
		for (Sequence seq : dao.getDomainSequences(identityDomain)) {
			seq.setSessionFactory(sf);
			sequences.put(seq.getName(), seq);
		}
		return this; 
	}

	@Autowired(required=false)
	SequencesConfig config;
	@Override
	public Sequences setSequenceConfig(SequencesConfig config) { this.config = config; return this; }

	private Sequence update(Sequence seq) {
		Session sess = null;
		try {
			
			sess = sf.openSession();
			sess.getTransaction().begin();
			sess.saveOrUpdate(seq);
			sess.getTransaction().commit();
		} finally {
			if (sess != null) { 
				if (sess.getTransaction()!=null) {
					if (sess.getTransaction().isActive()) { sess.getTransaction().rollback(); }
				}
				sess.close(); 
			}
		}
		return seq;
	}
	
	private Sequence insert(Sequence seq) {
		Session sess = null;
		try {
			sess = sf.openSession();
			sess.getTransaction().begin();
			sess.saveOrUpdate(seq);
			sess.getTransaction().commit();
		} finally {
			if (sess != null) { 
				if (sess.getTransaction()!=null) {
					if (sess.getTransaction().isActive()) { sess.getTransaction().rollback(); }
				}
				sess.close(); 
			}
		}
		return seq;
	}
	
	private Sequence getSequence(String sequence) {
		if (config == null) { config = SequencesConfigFactory.create(); }
		Sequence seq = sequences.get(sequence);
		if (seq == null) {
			seq = SequenceFactory.create(new SequenceID(sequence, identityDomain));
			seq.setSessionFactory(sf);
			sequences.put(seq.getName(), seq);
			SequenceConfig seqConf = config.getSequenceDefinition(sequence);
			if (seqConf == null) {
				seq.setInitialValue(config.getDefaultInitialValue());
				seq.setValue(config.getDefaultInitialValue());
				seq.setIncrement(config.getDefaultIncrement());
				seq.setStep(config.getDefaultStep());
			} else {
				seq.setInitialValue(seqConf.initialValue);
				seq.setValue(seqConf.initialValue);
				seq.setIncrement(seqConf.increment);
				seq.setStep(seqConf.step);
			}
			insert(seq);
		}
		return seq;
	}
	@Override
	public Long getNextValue(String sequence) {
		return getSequence(sequence).getNextValue();
	}

	@Override
	public Sequences setDefaultInitialValue(int initialValue) {
		if (config == null) { config = SequencesConfigFactory.create(); }
		config.setDefaultInitialValue(initialValue);
		return this;
	}

	@Override
	public Sequences setDefaultIncrement(int increment) {
		if (config == null) { config = SequencesConfigFactory.create(); }
		config.setDefaultIncrement(increment);
		return this;
	}

	@Override
	public Sequences setDefaultStep(int step) {
		if (config == null) { config = SequencesConfigFactory.create(); }
		config.setDefaultStep(step);
		return this;
	}

	@Override
	public Sequences setIncrement(String sequence, int increment) {
		Sequence seq = getSequence(sequence);
		seq.setIncrement(increment);
		update(seq);
		return this;
	}

	@Override
	public Sequences setStep(String sequence, int step) {
		Sequence seq = getSequence(sequence);
		seq.setStep(step);
		update(seq);
		return this;
	}

	@Override
	public Long getCurrentValue(String sequence) {
		return getSequence(sequence).getCurrentValue();
	}

	@Override
	public Sequences setCurrentValue(String sequence, Long currentValue) {
		Sequence seq = getSequence(sequence);
		seq.setCurrentValue(currentValue);
		update(seq);
		return this;
	}

}
