package com.k2.common.sequences;

import java.util.HashMap;
import java.util.Map;

public class EmptySequencesConfig implements SequencesConfig {
	
	protected Map<String, SequenceConfig> sequenceDefinitions = new HashMap<String, SequenceConfig>();

	@Override
	public Map<String, SequenceConfig> getSequenceDefinitions() {return sequenceDefinitions; }

	@Override
	public SequencesConfig setSequenceDefinition(SequenceConfig sequenceConfig) { 
		sequenceDefinitions.put(sequenceConfig.name, sequenceConfig);
		return this; 
	}

	@Override
	public SequenceConfig getSequenceDefinition(String sequence) {
		
		if (sequenceDefinitions.containsKey(sequence)) {
			return sequenceDefinitions.get(sequence);
		}
		SequenceConfig newSequenceConfig = new SequenceConfig(sequence, getDefaultInitialValue(), getDefaultIncrement(), getDefaultStep());
		sequenceDefinitions.put(newSequenceConfig.name, newSequenceConfig);
		return newSequenceConfig;
	}

	private int defaultIncrement = 1;
	@Override
	public int getDefaultIncrement() { return defaultIncrement; }
	@Override
	public SequencesConfig setDefaultIncrement(int defaultIncrement) { this.defaultIncrement = defaultIncrement; return this; }

	private int defaultStep = 10;
	@Override
	public int getDefaultStep() { return defaultStep; }
	@Override
	public SequencesConfig setDefaultStep(int defaultStep) { this.defaultStep = defaultStep; return this; }

	private long defaultInitialValue = 1L;
	@Override
	public long getDefaultInitialValue() { return defaultInitialValue; }
	@Override
	public SequencesConfig setDefaultInitialValue(long defaultInitialValue) { this.defaultInitialValue = defaultInitialValue; return this; }

}
