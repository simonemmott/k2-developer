package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.meta.MetaModelEntity;
import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.ComponentDAO;
import com.k2.dev.dao.K2ServiceDAO;
import com.k2.dev.model.Component;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.ComponentBO;
import com.k2.dev.model.bo.K2ServiceBO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.entity.K2SnippetContainerENT;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.K2ServiceService;

@Service("K2ServiceService")
@Transactional
public class K2ServiceServiceImpl extends GenericEntityService<K2ServiceENT, Long, K2Service> implements K2ServiceService {

	public static class Lists {
		
		private static abstract class K2ServiceServiceList extends GenericServiceList<K2ServiceENT, K2Service> implements ServiceList<K2Service> {
			protected K2ServiceDAO dao;
			protected K2ServiceService service;
			public K2ServiceServiceList(K2ServiceService service, K2ServiceDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.SERVICE; }
		}

		public static class All extends K2ServiceServiceList implements ServiceList<K2Service> {
			public All(K2ServiceService service, K2ServiceDAO dao) { super(service, dao); }
			@Override public K2Service newBO() { return service.newBO(); }
			@Override public K2Service newBO(Long id) { return service.newBO(id); }
			@Override protected List<K2ServiceENT> getList() { return dao.list(); }
			@Override protected K2Service getBO(K2ServiceENT entity) { return service.getBO(entity); }
		}
	}
	public static class Init {
				
	}


	public K2ServiceServiceImpl() {}
	
	@Autowired
	K2ServiceDAO dao;
	@Override
	protected K2ServiceDAO getDAO() { return dao; }

	
	@Override
	public K2Service nullBO() { return K2ServiceBO.NULL; }
	@Override
	public K2Service getBO(K2ServiceENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (K2Service) serviceContext.getBO(entity); }
		return (K2Service) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public K2Service newBO(Long id, EntityInitialValues<K2ServiceENT> init) { 
		if (id == null) {
			return (K2Service) serviceContext.putBO(new K2ServiceBO(prepareNewEntity(new K2ServiceENT(), "K2Service.ID", init), PersistenceState.NEW)); 
		} else {
			return (K2Service) serviceContext.putBO(new K2ServiceBO(prepareNewEntity(new K2ServiceENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public K2Service newK2Service() { return super.newBO(); }
	@Override
	public K2Service fetchK2Service(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<K2Service> listAll() { return new Lists.All(this, dao); }


}
