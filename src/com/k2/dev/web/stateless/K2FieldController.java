package com.k2.dev.web.stateless;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.interaction.Conversation;
import com.k2.common.interaction.CurrentConversation;
import com.k2.common.interaction.Message;
import com.k2.common.interaction.RequestConversation;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaList;
import com.k2.common.meta.MetaMethod;
import com.k2.common.service.ServiceModel;
import com.k2.common.web.AbstractStatelessEntityController;
import com.k2.common.writeEvents.ValidationException;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.meta.component.MetaK2Entity;
import com.k2.dev.model.meta.component.MetaK2Field;
import com.k2.dev.service.K2EntityService;
import com.k2.dev.service.K2FieldService;

@Controller
public class K2FieldController extends AbstractStatelessEntityController<K2Field> {
	
	@PersistenceUnit
	EntityManagerFactory emf;

	@Autowired
	CurrentConversation currentConversation;
	
	@Autowired
	private K2FieldService service;
	
	@Autowired
	public K2FieldController(OutputFormatterContextFactory formatterContextFactory, ApplicationContext snippetContext) {
		super(formatterContextFactory, snippetContext);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<MetaField> getMetaFields() { return MetaK2Field.Fields.getFields(); }
	
	@Override
	@SuppressWarnings("rawtypes")
	protected MetaField getMetaField(String parmName) { return  MetaK2Field.Fields.getMetaField(parmName); }
	
	@Override
	@SuppressWarnings("rawtypes")
	protected MetaList getMetaList(String listAlias) { return  MetaK2Field.Lists.getMetaList(listAlias); }
	
	@SuppressWarnings("rawtypes")
	@Override
	protected List<MetaList> getMetaLists() { return MetaK2Field.Lists.getLists(); }
	
	@SuppressWarnings("rawtypes")
	@Override
	protected MetaMethod getMetaMethod(String methodAlias) { return MetaK2Field.Methods.getMetaMethod(methodAlias); }

	@SuppressWarnings("rawtypes")
	@Override
	protected List<MetaMethod> getMetaMethods() { return MetaK2Field.Methods.getMethods(); }
	
	@RequestMapping(value="/entities/k2Field", method=RequestMethod.GET)
	public String getEntityList(
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {

		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin());
		
		try {
		
			List<Message> messages = getMessages(model);
		
			presentEntityList(service.listAll(), MetaK2Field.defaultFieldSet, messages, request, response);

		} finally {
			conversation.end();
		}
		
		return null;
	}
	
	@RequestMapping(value="/entities/k2Field/{id}", method=RequestMethod.POST)
	public void postEntity(
			@RequestParam(value="k2-action", required=false) String k2Action, 
			@RequestParam(value="k2-from", required=false) String k2From, 
			@PathVariable("id") Long id,
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {

		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin());
		
		try {
			List<Message> messages = getMessages(model);

			K2Field entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }
			
			if (k2Action != null) {
				switch(k2Action) {
				case "ok":
					updateEntity(entity, messages, request);
					break;
				case "delete":
					try {
						entity.delete();
					} catch (ValidationException e) {
						messages.add(new Message(Message.Level.WARNING, e.getMessage()));
					}
					break;
					
				}
			}
			
			model.addFlashAttribute("messages", messages);
			goBack(k2From, request, response);
			
		} finally {
			conversation.end();
		}
	}
	
	@RequestMapping(value="/entities/k2Field/{id}", method=RequestMethod.GET)
	public void getEntity(
			@PathVariable("id") Long id, 
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin("get-k2Entity").readOnly());

		try {

			List<Message> messages = getMessages(model);

			K2Field entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }
			
			presentEntity(entity, messages, request, response);
			
		} finally {
			conversation.end();
		}
		return;
	}
	
	@RequestMapping(value="/entities/k2Field/new", method=RequestMethod.POST)
	public void postNewEntity(
			@RequestParam(value="k2-from", required=false) String k2From, 
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin());

		try {

			List<Message> messages = getMessages(model);
			Long entityID = (Long) model.getFlashAttributes().get(FLASH_ATTR_ENTITY_ID);

			K2Field entity = service.listAll().newBO(entityID);
			
			updateEntity(entity, messages, request);
			
			goBack(k2From, request, response);
			
		} finally {
			conversation.end();
		}
		return;
	}
	
	@RequestMapping(value="/entities/k2Field/new", method=RequestMethod.GET)
	public void getNewEntity(
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin("new-k2Entity").readOnly());

		try {

			List<Message> messages = getMessages(model);

			K2Field entity = service.listAll().newBO();
			
			model.addFlashAttribute(FLASH_ATTR_ENTITY_ID, entity.getID());
			
			presentEntity(entity, messages, request, response);

		} finally {
			conversation.end();
		}
		return;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/entities/k2Field/{id}/list/{listAlias}", method=RequestMethod.GET)
	public void getListFromEntity(
			@PathVariable("id") Long id, 
			@PathVariable("listAlias") String listAlias,
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin());
		
		try {
		
			List<Message> messages = getMessages(model);

			K2Field entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }
			
			MetaList metaList = MetaK2Entity.Lists.getMetaList(listAlias);

			presentEntityList(metaList.getHandler(entity).getServiceList(), metaList.fieldSet, messages, request, response);

		} finally {
			conversation.end();
		}
		
		return;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/entities/k2Field/{id}/list/{listAlias}/new", method=RequestMethod.POST)
	public void postNewEntityInList(
			@RequestParam(value="k2-from", required=false) String k2From, 
			@PathVariable("id") Long id, 
			@PathVariable("listAlias") String listAlias,
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin());

		try {

			List<Message> messages = getMessages(model);
			
			MetaList metaList = MetaK2Entity.Lists.getMetaList(listAlias);
						
			Long entityID = (Long) model.getFlashAttributes().get(FLASH_ATTR_ENTITY_ID);
			
			K2Field entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }

			ServiceModel newEntity = (ServiceModel) metaList.getHandler(entity).getServiceList().newBO(entityID);
			
			AbstractStatelessEntityController newEntityController = (AbstractStatelessEntityController) appContext.getBean(metaList.statelessControllerClass);
			
			newEntityController.updateEntity(newEntity, messages, request);
			
			goBack(k2From, request, response);
			
		} finally {
			conversation.end();
		}
		return;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/entities/k2Field/{id}/list/{listAlias}/new", method=RequestMethod.GET)
	public void getNewEntityInList(
			@PathVariable("id") Long id, 
			@PathVariable("listAlias") String listAlias,
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin("new-k2Entity").readOnly());

		try {

			List<Message> messages = getMessages(model);

			K2Field entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }
			
			MetaList metaList = MetaK2Entity.Lists.getMetaList(listAlias);
			
			ServiceModel newEntity = (ServiceModel) metaList.getHandler(entity).getServiceList().newBO();
			
			model.addFlashAttribute(FLASH_ATTR_ENTITY_ID, newEntity.getID());
			
			AbstractStatelessEntityController newEntityController = (AbstractStatelessEntityController) appContext.getBean(metaList.statelessControllerClass);
			
			newEntityController.presentEntity(newEntity, messages, request, response);

		} finally {
			conversation.end();
		}
		return;
	}
	
}
