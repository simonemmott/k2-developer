package com.k2.common.sequences;

import javax.persistence.EntityManagerFactory;

public interface Sequences {
		
	public Sequences setDAO(SequenceDAO dao);
	public Sequences setEntityManagerFactory(EntityManagerFactory emf);

	public Long getIdentityDomain();
	public Sequences setIdentityDomain(Long domain);
	
	public Long getNextValue(String sequence);
	public Sequences setDefaultInitialValue(int initialValue);
	public Sequences setDefaultIncrement(int increment);
	public Sequences setDefaultStep(int step);
	public Sequences setIncrement(String sequence, int increment);
	public Sequences setStep(String sequence, int step);
	public Long getCurrentValue(String sequence);
	public Sequences setCurrentValue(String sequence, Long currentValue);
	public Sequences setSequenceConfig(SequencesConfig config);


	

}
