package com.k2.common.sequences;

public class SequenceConfig {
	
	public String name;
	public long initialValue;
	public int increment;
	public int step;
	
	public SequenceConfig(String name, long initialValue2, int increment, int step) {
		this.name = name;
		this.initialValue = initialValue2;
		this.increment = increment;
		this.step = step;
	}

}
