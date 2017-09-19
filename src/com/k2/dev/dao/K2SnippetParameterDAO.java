package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.entity.K2SnippetParameterENT;

public interface K2SnippetParameterDAO extends GenericDAO<K2SnippetParameterENT, Long> {

	public List<K2SnippetParameterENT> listForSnippet(K2SnippetENT snippet);

	public K2SnippetParameterENT fetchForSnippetAndName(K2SnippetENT snippet, String name);

}
