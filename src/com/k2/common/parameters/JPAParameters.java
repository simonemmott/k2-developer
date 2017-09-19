package com.k2.common.parameters;

import java.util.ArrayList;
import java.util.List;

public class JPAParameters {

	public static final String DATABASE="jpa.database";
	public static final String SHOW_SQL="jpa.show_sql";
	public static final String GENERATE_DDL="jpa.generate_ddl";
	public static final String DATABASE_PLATFORM="jpa.database_platform";

	public static final List<String> propertyList() {
		List<String> propertyList = new ArrayList<String>();
		propertyList.add(DATABASE);
		propertyList.add(SHOW_SQL);
		propertyList.add(GENERATE_DDL);
		propertyList.add(DATABASE_PLATFORM);
		return propertyList;
	}


}
