package com.k2.dev.model;

import com.k2.common.identity.ID;
import com.k2.common.service.ServiceList;
import com.k2.common.service.ServiceModel;
import com.k2.dev.model.entity.K2ListENT;

@SuppressWarnings({"rawtypes"})
public interface K2List  extends ServiceModel, ID {
	
	// Service methods -----
	@Override
	public K2ListENT getEntity();
	@Override
	public boolean isNull();
	@Override
	public K2List Null();

	// Fields -----------
	// ID Field
	public Long getId();
	public void setId(Long id);

	// Name field
	public String getAlias();
	public void setAlias(String name);

	// Name field
	public String getName();
	public void setName(String name);

	// ListOfEntity field
    public K2Entity getListOfEntity();
    public void setListOfEntity(K2Entity listOfEntity);
    
	// ListOfEntity field
    public K2Entity getK2Entity();
    public void setK2Entity(K2Entity listOfEntity);
    
    // Lists --------
    // Entities list
	public ServiceList<K2Entity> getEntities();
	
}