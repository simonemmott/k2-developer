package com.k2.dev.dao;

import java.util.List;

import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.K2SnippetContainerENT;

public interface K2SnippetContainerDAO extends GenericDAO<K2SnippetContainerENT, Long> {

	public List<K2SnippetContainerENT> listForSnippet(K2SnippetENT snippet);

	public K2SnippetContainerENT fetchForSnippetAndAlias(K2SnippetENT snippet, String name);

}
