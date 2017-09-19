package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2PermittedContent;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.entity.K2PermittedContentENT;

public interface K2PermittedContentService extends EntityService<K2PermittedContentENT, Long, K2PermittedContent>  {

	public K2PermittedContent newK2PermittedContent();
	public K2PermittedContent fetchK2PermittedContent(Long id);

	public ServiceList<K2PermittedContent> listForSnippetContainer(K2SnippetContainer snippetContainer);

	public K2PermittedContent fetchForSnippetContainerAndName(K2SnippetContainer cont, String s);


}
