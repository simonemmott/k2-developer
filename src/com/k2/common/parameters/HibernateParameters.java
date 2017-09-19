package com.k2.common.parameters;

import java.util.ArrayList;
import java.util.List;

public class HibernateParameters {

	public static final String HIBERNATE_DIALECT="hibernate.dialect";
	public static final String HIBERNATE_SHOW_SQL="hibernate.show_sql";
	public static final String HIBERNATE_JDBC_FETCH_SIZE="hibernate.jdbc.fetch_size";
	public static final String HIBERNATE_JDBC_BATCH_SIZE="hibernate.jdbc.batch_size";
	public static final String HIBERNATE_HBM2DDL="hibernate.hbm2ddl.auto";
	public static final String HIBERNATE_CONNECTION_POOL_SIZE="hibernate.connection.pool_size";
	public static final String HIBERNATE_ORDER_UPDATES="hibernate.order_updates";
	public static final String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS="hibernate.current_session_context_class";

	public static final List<String> propertyList() {
		List<String> propertyList = new ArrayList<String>();
		propertyList.add(HIBERNATE_DIALECT);
		propertyList.add(HIBERNATE_SHOW_SQL);
		propertyList.add(HIBERNATE_JDBC_FETCH_SIZE);
		propertyList.add(HIBERNATE_JDBC_BATCH_SIZE);
		propertyList.add(HIBERNATE_HBM2DDL);
		propertyList.add(HIBERNATE_CONNECTION_POOL_SIZE);
		propertyList.add(HIBERNATE_ORDER_UPDATES);
		propertyList.add(HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS);
		return propertyList;
	}
}
