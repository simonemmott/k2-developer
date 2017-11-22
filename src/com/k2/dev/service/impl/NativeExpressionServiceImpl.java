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
import com.k2.dev.dao.ImplementedExpressionDAO;
import com.k2.dev.dao.K2EntityDAO;
import com.k2.dev.dao.NativeExpressionDAO;
import com.k2.dev.model.EntityFormatter;
import com.k2.dev.model.ImplementedExpression;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.NativeExpression;
import com.k2.dev.model.bo.EntityFormatterBO;
import com.k2.dev.model.bo.ImplementedExpressionBO;
import com.k2.dev.model.bo.K2EntityBO;
import com.k2.dev.model.bo.NativeExpressionBO;
import com.k2.dev.model.entity.EntityFormatterENT;
import com.k2.dev.model.entity.ImplementedExpressionENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.NativeExpressionENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ImplementedExpressionService;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.NativeExpressionService;

@Service("NativeExpressionService")
@Transactional
public class NativeExpressionServiceImpl extends GenericEntityService<NativeExpressionENT, Long, NativeExpression> implements NativeExpressionService{

	public static class Lists {
		private static abstract class NativeExpressionServiceList extends GenericServiceList<NativeExpressionENT, NativeExpression> implements ServiceList<NativeExpression> {
			protected NativeExpressionDAO dao;
			protected NativeExpressionService service;
			public NativeExpressionServiceList(NativeExpressionService service, NativeExpressionDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.NATIVE_EXPRESSION; }
		}

		public static class All extends NativeExpressionServiceList implements ServiceList<NativeExpression> {
			public All(NativeExpressionService service, NativeExpressionDAO dao) { super(service, dao); }
			@Override public NativeExpression newBO() { return service.newBO(); }
			@Override public NativeExpression newBO(Long id) { return service.newBO(id); }
			@Override protected List<NativeExpressionENT> getList() { return dao.list(); }
			@Override protected NativeExpression getBO(NativeExpressionENT entity) { return service.getBO(entity); }
		}
	}

	public NativeExpressionServiceImpl() {}

	@Autowired
	private NativeExpressionDAO dao;
	@Override
	protected NativeExpressionDAO getDAO() { return dao; }
	@Override
	public NativeExpression nullBO() { return NativeExpressionBO.NULL; }
	
	@Override
	public ServiceList<NativeExpression> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public NativeExpression newBO(Long id, EntityInitialValues<NativeExpressionENT> init) { 
		if (id == null) {
			return (NativeExpression) serviceContext.putBO(new NativeExpressionBO(prepareNewEntity(new NativeExpressionENT(), "Expression.ID", init), PersistenceState.NEW)); 
		} else {
			return (NativeExpression) serviceContext.putBO(new NativeExpressionBO(prepareNewEntity(new NativeExpressionENT(), id, init), PersistenceState.NEW)); 
		}
	}


	
	@Override
	public NativeExpression getBO(NativeExpressionENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (NativeExpression) serviceContext.getBO(entity); }
		return (NativeExpression) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public NativeExpression newNativeExpression() { return super.newBO(); }
	@Override
	public NativeExpression fetchNativeExpression(Long id) { return super.fetch(id); }

}
