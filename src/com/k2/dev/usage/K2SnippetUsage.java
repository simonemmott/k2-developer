package com.k2.dev.usage;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.k2.common.usage.BasicEntityUsage;
import com.k2.common.usage.EntityUsage;
import com.k2.common.usage.ForeignKeyDefinition;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.entity.K2SnippetContainerENT;
import com.k2.dev.model.entity.K2SnippetParameterENT;

@Component("K2SnippetUsage")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class K2SnippetUsage extends BasicEntityUsage<K2SnippetENT> implements EntityUsage<K2SnippetENT>{

	public K2SnippetUsage() {
		System.out.println("Creating usage details for: K2Snippet");

		foreignKeys.add(new ForeignKeyDefinition<K2SnippetENT, K2SnippetParameterENT>(
				K2SnippetENT.class, 
				K2SnippetParameterENT.class, 
				"widget", 
				ForeignKeyDefinition.Cascade.DELETE));
		foreignKeys.add(new ForeignKeyDefinition<K2SnippetENT, K2SnippetContainerENT>(
				K2SnippetENT.class, 
				K2SnippetContainerENT.class, 
				"widget", 
				ForeignKeyDefinition.Cascade.DELETE));
	}

}
