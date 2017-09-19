package com.k2.dev.dao;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.K2SnippetENT;

public interface K2SnippetDAO extends GenericDAO<K2SnippetENT, Long> {

	public K2SnippetENT fetchForName(String name);

}
