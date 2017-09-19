package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.Template;
import com.k2.dev.model.entity.K2SnippetParameterENT;

public interface K2SnippetParameterService extends EntityService<K2SnippetParameterENT, Long, K2SnippetParameter> {

	public K2SnippetParameter newK2SnippetParameter();
	public K2SnippetParameter fetchK2SnippetParameter(Long id);

	public ServiceList<K2SnippetParameter> listForSnippet(K2Snippet k2Snippet);

	public K2SnippetParameter fetchForSnippetAndName(K2Snippet k2Snippet, String name);

	public ServiceList<K2SnippetParameter> listForTemplate(Template template);

}
