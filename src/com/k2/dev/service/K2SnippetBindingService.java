package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.dev.model.K2SnippetBinding;
import com.k2.dev.model.entity.K2SnippetBindingENT;

public interface K2SnippetBindingService extends EntityService<K2SnippetBindingENT, Long, K2SnippetBinding> {

	public K2SnippetBinding newK2SnippetBinding();
	public K2SnippetBinding fetchK2SnippetBinding(Long id);

}
