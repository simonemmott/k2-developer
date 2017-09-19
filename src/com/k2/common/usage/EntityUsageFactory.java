package com.k2.common.usage;

public class EntityUsageFactory {

	public static EntityUsage<Object> unused() {
		return new EmptyEntityUsage<Object>();
	}

}
