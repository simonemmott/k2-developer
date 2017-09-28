package com.k2.dev.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2.common.service.EntityInitialValues;
import com.k2.common.service.GenericEntityService;
import com.k2.common.service.GenericServiceList;
import com.k2.common.service.ServiceList;
import com.k2.common.service.GenericServiceModel.PersistenceState;
import com.k2.dev.dao.LiteralDAO;
import com.k2.dev.model.Literal;
import com.k2.dev.model.bo.LiteralBO;
import com.k2.dev.model.entity.LiteralENT;
import com.k2.dev.service.LiteralService;

@Service("literalService")
@Transactional
public class LiteralServiceImpl extends GenericEntityService<LiteralENT, Long, Literal> implements LiteralService{

	public static class Lists {
		
		private static abstract class LiteralServiceList extends GenericServiceList<LiteralENT, Literal> implements ServiceList<Literal> {
			protected LiteralDAO dao;
			protected LiteralService service;
			public LiteralServiceList(LiteralService service, LiteralDAO dao) { this.service = service; this.dao = dao; }
		}

		public static class All extends LiteralServiceList implements ServiceList<Literal> {
			public All(LiteralService service, LiteralDAO dao) { super(service, dao); }
			@Override public Literal newBO() { return service.newBO(); }
			@Override protected List<LiteralENT> getList() { return dao.list(); }
			@Override protected Literal getBO(LiteralENT entity) { return service.getBO(entity); }
		}

	}

	public LiteralServiceImpl() {}

	@Autowired
	private LiteralDAO dao;
	@Override
	protected LiteralDAO getDAO() { return dao; }
	@Override
	public Literal nullBO() { return LiteralBO.NULL; }
	
	@Override
	public ServiceList<Literal> listAll() { return new Lists.All(this, dao); }
	
	@Override
	public Literal newBO(EntityInitialValues<LiteralENT> init) {
		return (Literal) serviceContext.putBO(new LiteralBO(prepareNewEntity(new LiteralENT(), "Literal.ID", init), PersistenceState.NEW)); 
	}
	
	@Override
	public Literal getBO(LiteralENT entity) {
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (Literal) serviceContext.getBO(entity); }
		return (Literal) serviceContext.putBO(new LiteralBO(entity, PersistenceState.PERSISTED));
	}
	@Override
	public Literal newLiteral() { return super.newBO(); }
	@Override
	public Literal fetchLiteral(Long id) { return super.fetch(id); }


}
