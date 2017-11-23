package com.k2.dev.usage;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.k2.common.usage.BasicEntityUsage;
import com.k2.common.usage.EntityUsage;
import com.k2.common.usage.ForeignKeyDefinition;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;

@Component("K2EntityUsage")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class K2EntityUsage extends BasicEntityUsage<K2EntityENT> implements EntityUsage<K2EntityENT>{

	public K2EntityUsage() {
		System.out.println("Creating usage details for: K2Entity");

		foreignKeys.add(new ForeignKeyDefinition<K2EntityENT, K2FieldENT>(
				K2EntityENT.class, 
				K2FieldENT.class, 
				"k2Entity", 
				ForeignKeyDefinition.Cascade.DELETE));
		foreignKeys.add(new ForeignKeyDefinition<K2EntityENT, K2EntityENT>(
				K2EntityENT.class, 
				K2EntityENT.class, 
				"extendsEntity", 
				ForeignKeyDefinition.Cascade.BLANK));
	}
}
