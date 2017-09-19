package com.k2.dev.service;

import com.k2.common.service.EntityService;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.entity.K2SnippetENT;

public interface K2SnippetService extends EntityService<K2SnippetENT, Long, K2Snippet> {
	
	public K2Snippet newK2Snippet();
	public K2Snippet fetchK2Snippet(Long id);

	public K2Snippet fetchForName(String name);


}
