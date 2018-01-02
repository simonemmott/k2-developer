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
import com.k2.dev.dao.FieldSetMemberDAO;
import com.k2.dev.dao.K2FieldSetDAO;
import com.k2.dev.model.Component;
import com.k2.dev.model.FieldSetMember;
import com.k2.dev.model.K2FieldSet;
import com.k2.dev.model.K2Service;
import com.k2.dev.model.bo.ComponentBO;
import com.k2.dev.model.bo.FieldSetMemberBO;
import com.k2.dev.model.bo.K2FieldSetBO;
import com.k2.dev.model.bo.K2ServiceBO;
import com.k2.dev.model.entity.ComponentENT;
import com.k2.dev.model.entity.FieldSetMemberENT;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.model.entity.K2FieldSetENT;
import com.k2.dev.model.entity.K2ServiceENT;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.service.ComponentService;
import com.k2.dev.service.FieldSetMemberService;
import com.k2.dev.service.K2FieldSetService;
import com.k2.dev.service.impl.K2FieldServiceImpl.Init;

@Service("FieldSetMemberService")
@Transactional
public class FieldSetMemberServiceImpl extends GenericEntityService<FieldSetMemberENT, Long, FieldSetMember> implements FieldSetMemberService {

	public static class Lists {
		
		private static abstract class FieldSetMemberServiceList extends GenericServiceList<FieldSetMemberENT, FieldSetMember> implements ServiceList<FieldSetMember> {
			protected FieldSetMemberDAO dao;
			protected FieldSetMemberService service;
			public FieldSetMemberServiceList(FieldSetMemberService service, FieldSetMemberDAO dao) { this.service = service; this.dao = dao; }
			@Override public MetaModelEntity getMetaEntity() { return MetaModel.Entities.FIELD_SET_MEMBER; }
		}

		public static class All extends FieldSetMemberServiceList implements ServiceList<FieldSetMember> {
			public All(FieldSetMemberService service, FieldSetMemberDAO dao) { super(service, dao); }
			@Override public FieldSetMember newBO() { return service.newBO(); }
			@Override public FieldSetMember newBO(Long id) { return service.newBO(id); }
			@Override protected List<FieldSetMemberENT> getList() { return dao.list(); }
			@Override protected FieldSetMember getBO(FieldSetMemberENT entity) { return service.getBO(entity); }
		}

		public static class ForFieldSet extends FieldSetMemberServiceList implements ServiceList<FieldSetMember> {
			private K2FieldSet k2FieldSet;
			public ForFieldSet(FieldSetMemberService service, FieldSetMemberDAO dao, K2FieldSet k2FieldSet) { 
				super(service, dao); 
				this.k2FieldSet = k2FieldSet;
			}
			@Override public FieldSetMember newBO() { return service.newBO(null, new Init.ListForFieldSet(k2FieldSet.getEntity())); }
			@Override public FieldSetMember newBO(Long id) { return service.newBO(id, new Init.ListForFieldSet(k2FieldSet.getEntity())); }
			@Override protected List<FieldSetMemberENT> getList() { return dao.listForFieldSet(k2FieldSet.getEntity()); }
			@Override protected FieldSetMember getBO(FieldSetMemberENT entity) { return service.getBO(entity); }
		}

	}
	public static class Init {
		
		public static class ListForFieldSet extends EntityInitialValues<FieldSetMemberENT> {
			private K2FieldSetENT k2FieldSet;
			public ListForFieldSet(K2FieldSetENT k2FieldSet) {
				this.k2FieldSet = k2FieldSet;
			}
			@Override public void initialize(FieldSetMemberENT fieldSetMember) { fieldSetMember.setFieldSet(k2FieldSet); }
		}
		
	}


	public FieldSetMemberServiceImpl() {}
	
	@Autowired
	FieldSetMemberDAO dao;
	@Override
	protected FieldSetMemberDAO getDAO() { return dao; }

	
	@Override
	public FieldSetMember nullBO() { return FieldSetMemberBO.NULL; }
	@Override
	public FieldSetMember getBO(FieldSetMemberENT entity) { 
		if (entity == null ) { return nullBO(); }
		if (serviceContext.getBO(entity) != null) { return (FieldSetMember) serviceContext.getBO(entity); }
		return (FieldSetMember) serviceContext.putBO(entity.getServiceModel(PersistenceState.PERSISTED));
	}
	@Override
	public FieldSetMember newBO(Long id, EntityInitialValues<FieldSetMemberENT> init) { 
		if (id == null) {
			return (FieldSetMember) serviceContext.putBO(new FieldSetMemberBO(prepareNewEntity(new FieldSetMemberENT(), "FieldSetMember.ID", init), PersistenceState.NEW)); 
		} else {
			return (FieldSetMember) serviceContext.putBO(new FieldSetMemberBO(prepareNewEntity(new FieldSetMemberENT(), id, init), PersistenceState.NEW)); 
		}
	}

	@Override
	public FieldSetMember newFieldSetMember() { return super.newBO(); }
	@Override
	public FieldSetMember fetchFieldSetMember(Long id) { return super.fetch(id); }

	@Override
	public ServiceList<FieldSetMember> listAll() { return new Lists.All(this, dao); }


	@Override
	public ServiceList<FieldSetMember> listForFieldSet(K2FieldSet k2FieldSet) { return new Lists.ForFieldSet(this, dao, k2FieldSet); }


}
