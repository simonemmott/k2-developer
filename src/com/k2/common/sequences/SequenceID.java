package com.k2.common.sequences;

import java.io.Serializable;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.gson.annotations.Expose;

@Embeddable
public class SequenceID implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4563812518457712099L;
	@Column(name="Name", nullable=false, length=50)
	@Expose(serialize=true)
	protected String name;
	public String getName() { return name; }
	@Column(name="IdentityDomain", nullable=false)
	@Expose(serialize=true)
	protected Long identityDomain;
	public Long getIdentityDomain() { return identityDomain; }
	
	protected SequenceID() {}
	
	public SequenceID(String name, Long identityDomain) {
		this.name = name;
		this.identityDomain = identityDomain;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) { return false; }
 		if (this == other) { return true; }
 		if (getClass().equals(other.getClass())) {
 			SequenceID that = (SequenceID)other;
 			return (Objects.equal(that.name, this.name)&&Objects.equal(that.identityDomain, this.identityDomain));
 		}
 		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.name, this.identityDomain);
	}

}
