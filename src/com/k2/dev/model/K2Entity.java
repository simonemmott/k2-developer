package com.k2.dev.model;

import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2EntityENT;

@SuppressWarnings("rawtypes")
public interface K2Entity  extends ServiceModel, Component {

	@Override
	public boolean isNull();

	@Override
	public K2Entity Null();
	
	public String getEntityName();
	
	public void setEntityName(String entityName);
	
	public String getTableName();
	
	public void setTableName(String tableName);
	
	@Override public K2EntityENT getEntity();

	public K2Entity getExtendsEntity();
	
	public void setExtendsEntity(K2Entity extendsEntity);

	public String getInheritanceJoinColumn();
	
	public void setInheritanceJoinColumn(String joinColumn);

	public ServiceList<K2Field> getFields();
	
	public ServiceList<K2Entity> getExtendableEntities();

	public void test(String test, K2Field field);

}