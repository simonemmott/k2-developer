package com.k2.dev.dao;

import java.util.List;

import com.k2.common.dataAccess.GenericDAO;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;

public interface K2FieldDAO extends GenericDAO<K2FieldENT, Long> {

	public List<K2FieldENT> listForEntity(K2EntityENT entity);

}
