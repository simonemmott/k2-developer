package com.k2.common.sequences;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;

public interface SequenceDAO extends GenericDAO<SequenceENT, Long> {
	
	public List<SequenceENT> getDomainSequences(Long domainId);

}
