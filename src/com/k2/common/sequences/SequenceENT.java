package com.k2.common.sequences;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="K2Sequences")
public class SequenceENT implements Sequence {

	public static Sequence NULL = new SequenceENT();
	public static Sequence Null() { return NULL; }
	@Override
	public boolean isNull() { return (this == NULL); }
	
//	@Transient
//	SequenceDAO<SequenceImpl, SequenceID> dao = new SequenceHibernateDAO<SequenceImpl, SequenceID>();
	
	@Transient
	SessionFactory sf;
	@Override
	public Sequence setSessionFactory(SessionFactory sf) { this.sf = sf; return this; }
	
	@Override
	public boolean equals(Object other) {
		if (other==null) { return false; }
		if (this==other) { return true; }
		if (getClass() != other.getClass()) { return false; }
		final Sequence that = (Sequence) other;
		return Objects.equals(this.getName(), that.getName());
	}
	@Override
	public int hashCode() { return Objects.hashCode(getName()); }
	
    @Override
    public String getIdentity(){ return getName(); }
    @Override
    public String getFullIdentity() { return getName(); }
    
    public SequenceENT() {}
    public SequenceENT(SequenceID id) { this.id = id; }
    
    
    @EmbeddedId
    protected SequenceID id;
    @Override
    public SequenceID getID() { return id; }
    @Override
    public String getName() { return id.name; }
    @Override
    public Long getIdentityDomain() { return id.identityDomain; }
    
    @Column(name="IdentityDomain", insertable=false, updatable=false)
    @Expose(serialize=false)
    Long identityDomain;
        
	@Column(name="Value", nullable=false)
	@Expose(serialize=true)
	private Long value = 0L;
	@Override
	public Long getValue() { return value; }
	@Override
	public Sequence setValue(Long value) { this.value = value; return this; }
	
	@Column(name="InitialValue", nullable=false)
	@Expose(serialize=true)
	private Long initialValue = 0L;
	@Override
	public Long getInitialValue() { return initialValue; }
	@Override
	public Sequence setInitialValue(Long initialValue) { this.initialValue = initialValue; return this; }
	

	@Column(name="Increment", nullable=false)
	@Expose(serialize=true)
	private int increment = 1;
	@Override
	public int getIncrement() { return increment; }
	@Override
	public Sequence setIncrement(int increment) { this.increment = increment; return this; }

	@Column(name="Step", nullable=false)
	@Expose(serialize=true)
	private int step = 10;
	@Override
	public int getStep() { return step; }
	@Override
	public Sequence setStep(int step) { this.step = step; return this; }

	@Override
	public SequenceConfig getConfig() {
		return new SequenceConfig(getName(), initialValue, increment, step);
	}
	@Override
	public synchronized Long getNextValue() {
		if (getCurrentValue()+increment <= value) {
			currentValue = getCurrentValue() + increment;
			return getIdentityDomain()+currentValue;
		} else {
			Session sess = null;
			if (sf == null) { throw new SequenceError("Session factory not set when cycling sequence!"); }
			try {
				
				sess = sf.openSession();
				Sequence currentSeq = (Sequence) sess.get(SequenceENT.class, this.getID());
				sess.getTransaction().begin();
//				sess.load(currentSeq, this.getID(), LockModeType.PESSIMISTIC_READ);
				value = currentSeq.getValue();
				increment = currentSeq.getIncrement();
				initialValue = currentSeq.getInitialValue();
				step = currentSeq.getStep();
				currentValue = value;
				
				value = value + step;
				currentSeq.setValue(value);
				sess.saveOrUpdate(currentSeq);
				sess.flush();
				sess.getTransaction().commit();
			} finally {
				if (sess != null) { sess.close(); }
			}
			
			return getNextValue();
			
		}
	}
	
	@Transient
	private Long currentValue;
	@Override
	public Long getCurrentValue() {
		if (currentValue == null) { currentValue = value; }
		return currentValue;
	}
	@Override
	public Sequence setCurrentValue(Long currentValue) { this.currentValue = currentValue; return this; }

}
