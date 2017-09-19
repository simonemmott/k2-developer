package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.K2PermittedContentENT;
import com.k2.dev.model.entity.K2SnippetContainerENT;

public interface K2PermittedContentDAO extends GenericDAO<K2PermittedContentENT, Long> {

	public List<K2PermittedContentENT> listForSnippetContainer(K2SnippetContainerENT snippetContainer);

	public K2PermittedContentENT fetchForSnippetContainerAndName(K2SnippetContainerENT cont, String name);

}
