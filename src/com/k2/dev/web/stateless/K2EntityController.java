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
import com.k2.common.interaction.MethodHandler;
import com.k2.common.interaction.RequestConversation;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaList;
import com.k2.common.meta.MetaMethod;
import com.k2.common.meta.MetaMethodParameter;
import com.k2.common.service.ServiceModel;
import com.k2.common.web.AbstractStatelessEntityController;
import com.k2.common.writeEvents.ValidationException;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.meta.component.MetaK2Entity;
import com.k2.dev.service.K2EntityService;

@Controller
public class K2EntityController extends AbstractStatelessEntityController<K2Entity> {
	
	@PersistenceUnit
	EntityManagerFactory emf;

	@Autowired
	CurrentConversation currentConversation;
	
	@Autowired
	private K2EntityService service;
	
	@Autowired
	public K2EntityController(OutputFormatterContextFactory formatterContextFactory, ApplicationContext snippetContext) {
		super(formatterContextFactory, snippetContext);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List<MetaField> getMetaFields() { return MetaK2Entity.Fields.getFields(); }
	
	@Override
	@SuppressWarnings("rawtypes")
	protected MetaField getMetaField(String parmName) { return  MetaK2Entity.Fields.getMetaField(parmName); }
	
	@Override
	@SuppressWarnings("rawtypes")
	protected MetaList getMetaList(String listAlias) { return  MetaK2Entity.Lists.getMetaList(listAlias); }
	
	@SuppressWarnings("rawtypes")
	@Override
	protected List<MetaList> getMetaLists() { return MetaK2Entity.Lists.getLists(); }
	
	@SuppressWarnings("rawtypes")
	@Override
	protected MetaMethod getMetaMethod(String methodAlias) { return MetaK2Entity.Methods.getMetaMethod(methodAlias); }

	@SuppressWarnings("rawtypes")
	@Override
	protected List<MetaMethod> getMetaMethods() { return MetaK2Entity.Methods.getMethods(); }
	

	
	@RequestMapping(value="/entities/k2Entity", method=RequestMethod.GET)
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
		
			presentEntityList(service.listAll(), MetaK2Entity.defaultFieldSet, messages, request, response);

		} finally {
			conversation.end();
		}
		
		return null;
	}
	
	@RequestMapping(value="/entities/k2Entity/{id}", method=RequestMethod.POST)
	public void postEntity(
			@RequestParam(value="k2:action", required=false) String k2Action, 
			@RequestParam(value="k2:from", required=false) String k2From, 
			@PathVariable("id") Long id,
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {

		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin());
		
		try {
			List<Message> messages = getMessages(model);

			K2Entity entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }
			
			updateEntity(entity, messages, request);

			if (k2Action != null) {
				
				if (k2Action.startsWith("method:")) {
					String methodAlias = k2Action.split(":")[1];
					@SuppressWarnings("unchecked")
					MetaMethod<K2Entity> metaMethod = MetaK2Entity.Methods.getMetaMethod(methodAlias);
					if (metaMethod.methodParameters.size() == 0) {
						MethodHandler<K2Entity> methodHandler = metaMethod.getHandler(entity);
						methodHandler.execute();
					} else {
						setMessages(model, messages);
						goGetMethodParms(metaMethod, request, response);
						System.out.println("call to method with ("+metaMethod.methodParameters.size()+") parameters");
						return;
					}
				} else {
					switch(k2Action) {
					case "k2:ok":
						break;
					case "k2:delete":
						try {
							entity.delete();
						} catch (ValidationException e) {
							messages.add(new Message(Message.Level.WARNING, e.getMessage()));
						}
						break;
					}

				}

			}
			
			setMessages(model, messages);
			goBack(k2From, request, response);
			
		} finally {
			conversation.end();
		}
	}
	
	@RequestMapping(value="/entities/k2Entity/{id}", method=RequestMethod.GET)
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

			K2Entity entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }
			
			presentEntity(entity, messages, request, response);
			
		} finally {
			conversation.end();
		}
		return;
	}
	
	@RequestMapping(value="/entities/k2Entity/new", method=RequestMethod.POST)
	public void postNewEntity(
			@RequestParam(value="k2:from", required=false) String k2From, 
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin());

		try {

			List<Message> messages = getMessages(model);
			Long entityID = (Long) model.getFlashAttributes().get(FLASH_ATTR_ENTITY_ID);

			K2Entity entity = service.listAll().newBO(entityID);
			
			updateEntity(entity, messages, request);
			
			goBack(k2From, request, response);
			
		} finally {
			conversation.end();
		}
		return;
	}
	
	@RequestMapping(value="/entities/k2Entity/new", method=RequestMethod.GET)
	public void getNewEntity(
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin("new-k2Entity").readOnly());

		try {

			List<Message> messages = getMessages(model);

			K2Entity entity = service.listAll().newBO();
			
			model.addFlashAttribute(FLASH_ATTR_ENTITY_ID, entity.getID());
			
			presentEntity(entity, messages, request, response);

		} finally {
			conversation.end();
		}
		return;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/entities/k2Entity/{id}/list/{listAlias}", method=RequestMethod.GET)
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

			K2Entity entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }
			
			MetaList metaList = MetaK2Entity.Lists.getMetaList(listAlias);

			presentEntityList(metaList.getHandler(entity).getServiceList(), metaList.fieldSet, messages, request, response);

		} finally {
			conversation.end();
		}
		
		return;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/entities/k2Entity/{id}/list/{listAlias}/new", method=RequestMethod.POST)
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
			
			K2Entity entity = service.fetch(id);
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
	@RequestMapping(value="/entities/k2Entity/{id}/list/{listAlias}/new", method=RequestMethod.GET)
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

			K2Entity entity = service.fetch(id);
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

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/entities/k2Entity/{id}/method/{methodAlias}", method=RequestMethod.GET)
	public void getMethodParameters(
			@PathVariable("id") Long id, 
			@PathVariable("methodAlias") String methodAlias,
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin("new-k2Entity").readOnly());

		try {

			List<Message> messages = getMessages(model);

			K2Entity entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }
			
			MetaMethod metaMethod = MetaK2Entity.Methods.getMetaMethod(methodAlias);
			
			presentMethod(entity, metaMethod, messages, request, response);
						
		} finally {
			conversation.end();
		}
		return;
	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value="/entities/k2Entity/{id}/method/{methodAlias}", method=RequestMethod.POST)
	public void postMethodParameters(
			@PathVariable("id") Long id, 
			@PathVariable("methodAlias") String methodAlias,
			RedirectAttributes model,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		context.setContext(request);
		Conversation conversation = appContext.getBean(RequestConversation.class);
		currentConversation.set(conversation.begin("new-k2Entity").readOnly());

		try {

			List<Message> messages = getMessages(model);

			K2Entity entity = service.fetch(id);
			if (notFound(id, entity, response)) { return; }
			
			MetaMethod metaMethod = MetaK2Entity.Methods.getMetaMethod(methodAlias);
			
			setMessages(model, messages);
			
			executeMethod(entity, metaMethod, request, response);
			
			goBack(context.getContextRoot()+"/entities/k2Entity/"+id, request, response);
						
		} finally {
			conversation.end();
		}
		return;
	}

}
