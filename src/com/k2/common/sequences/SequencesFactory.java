package com.k2.common.sequences;

import javax.persistence.EntityManagerFactory;

public class SequencesFactory {
	
	public static Sequences create(EntityManagerFactory emf) {
		return new SequencesImpl().setEntityManagerFactory(emf);
	}
	
	public static Sequences create() {
		return new SequencesImpl();
	}

}
