package com.k2.common.sequences;

import java.util.Map;

public interface SequencesConfig {
	
//	public static SequencesConfig create() {
//		return new EmptySequencesConfig();
//	}
	
	public Map<String, SequenceConfig> getSequenceDefinitions();
	public SequencesConfig setSequenceDefinition(SequenceConfig sequenceConfig);
	public SequenceConfig getSequenceDefinition(String sequence);
	public int getDefaultIncrement();
	public SequencesConfig setDefaultIncrement(int defaultIncrement);
	public int getDefaultStep();
	public SequencesConfig setDefaultStep(int defaultStep);
	public long getDefaultInitialValue();
	public SequencesConfig setDefaultInitialValue(long defaultInitialValue);


}
