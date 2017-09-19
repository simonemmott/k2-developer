package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.Template;
import com.k2.dev.model.entity.K2SnippetContainerENT;

public interface K2SnippetContainerService extends EntityService<K2SnippetContainerENT, Long, K2SnippetContainer> {

	public K2SnippetContainer newK2SnippetContainer();
	public K2SnippetContainer fetchK2SnippetContainer(Long id);

	public ServiceList<K2SnippetContainer> listForSnippet(K2Snippet snippet);

	public K2SnippetContainer fetchForSnippetAndAlias(K2Snippet k2Snippet, String alias);

	public ServiceList<K2SnippetContainer> listForTemplate(Template template);

}
