package com.k2.common.sequences;

public class SequenceFactory {
	
	public static Sequence create() {
		return new SequenceENT();
	}
	public static Sequence create(SequenceID id) {
		return new SequenceENT(id);
	}

}
