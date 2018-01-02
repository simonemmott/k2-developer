package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.FieldSetMemberENT;
import com.k2.dev.model.entity.K2FieldSetENT;
import com.k2.dev.model.entity.K2ServiceENT;

public interface FieldSetMemberDAO extends GenericDAO<FieldSetMemberENT, Long> {

	List<FieldSetMemberENT> listForFieldSet(K2FieldSetENT fieldSet);

}
