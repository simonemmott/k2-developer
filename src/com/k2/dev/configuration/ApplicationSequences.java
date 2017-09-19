package com.k2.dev.configuration;

import com.k2.common.sequences.EmptySequencesConfig;
import com.k2.common.sequences.SequenceConfig;
import com.k2.common.sequences.SequencesConfig;

public class ApplicationSequences extends EmptySequencesConfig implements SequencesConfig {

	public ApplicationSequences() {
		
		setDefaultInitialValue(0L);
		setDefaultIncrement(1);
		setDefaultStep(10);

		sequenceDefinitions.put("MyFirstSequence", new SequenceConfig("MyFirstSequence", 990L, 10, 100));
		sequenceDefinitions.put("MySecondSequence", new SequenceConfig("MySecondSequence", 0L, 1, 10));
		sequenceDefinitions.put("AnotherSequence", new SequenceConfig("AnotherSequence", 48L, 2, 50));
	}


}
