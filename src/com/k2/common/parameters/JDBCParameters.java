package com.k2.common.parameters;

import java.util.ArrayList;
import java.util.List;

public class JDBCParameters {

	public static final String JDBC_DRIVER="jdbc.driver";
	public static final String JDBC_URL="jdcb.url";
	public static final String JDBC_USERNAME="jdbc.username";
	public static final String JDBC_PASSWORD="jdbc.password";

	public static final List<String> propertyList() {
		List<String> propertyList = new ArrayList<String>();
		propertyList.add(JDBC_DRIVER);
		propertyList.add(JDBC_URL);
		propertyList.add(JDBC_USERNAME);
		propertyList.add(JDBC_PASSWORD);
		return propertyList;
	}


}
