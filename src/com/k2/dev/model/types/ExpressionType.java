package com.k2.dev.model.types;

import com.k2.common.util.K2Type;

public enum ExpressionType  implements K2Type {
    NATIVE(98, "Native", "This expression is a native expression defined by K2."),
    IMPLEMENTED(99, "Implemented", "This expression implements another expression, either Native or Implemented");

	private int id;
	private String name;
	private String description;
	
	ExpressionType(int id, String name, String description) { 
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	@Override
	public int getId() { return id; }
	@Override
	public String getName() { return this.name; }
	@Override
	public String getDescription() { return this.description; }

}
