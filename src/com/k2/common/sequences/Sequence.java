package com.k2.common.sequences;

import org.hibernate.SessionFactory;

import com.k2.common.identity.Identity;
import com.k2.common.nulls.Nullable;

public interface Sequence extends Nullable, Identity {
	
//	public static Sequence create() {
//		return new SequenceENT();
//	}
//	public static Sequence create(SequenceID id) {
//		return new SequenceENT(id);
//	}
	
//	public static Sequence Null() { return SequenceENT.NULL; }

	public Sequence setSessionFactory(SessionFactory sf);

	public SequenceID getID();
	public String getName();
	public Long getIdentityDomain();
	
	public Long getValue();
	public Sequence setValue(Long value);

	public Long getInitialValue();
	public Sequence setInitialValue(Long initialValue);

	public int getIncrement();
	public Sequence setIncrement(int increment);
	
	public int getStep();
	public Sequence setStep(int step);
	
	public SequenceConfig getConfig();
	
	public Long getNextValue();
	public Sequence setCurrentValue(Long currentValue);
	public Long getCurrentValue();

}
