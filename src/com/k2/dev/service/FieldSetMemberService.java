package com.k2.dev.service;


import com.k2.common.service.EntityService;
import com.k2.common.service.ServiceList;
import com.k2.dev.model.Component;
import com.k2.dev.model.FieldSetMember;
import com.k2.dev.model.K2FieldSet;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.K2FieldSetBO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.FieldSetMemberENT;
import com.k2.dev.model.entity.K2FieldSetENT;

public interface FieldSetMemberService extends EntityService<FieldSetMemberENT, Long, FieldSetMember>{
	
	public FieldSetMember newFieldSetMember();
	public FieldSetMember fetchFieldSetMember(Long id);
	public ServiceList<FieldSetMember> listForFieldSet(K2FieldSet k2FieldSet);

}
