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
import com.k2.common.interaction.ContextManager;
import com.k2.common.interaction.Conversation;
import com.k2.common.interaction.CurrentConversation;
import com.k2.common.interaction.Message;
import com.k2.common.interaction.MethodHandler;
import com.k2.common.interaction.RequestConversation;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.meta.MetaField;
import com.k2.common.meta.MetaList;
import com.k2.common.meta.MetaMethod;
import com.k2.common.service.ServiceModel;
import com.k2.common.web.AbstractStatelessEntityController;
import com.k2.common.writeEvents.ValidationException;
import com.k2.common.usage.CascadeDeletionException;
import com.k2.dev.model.K2LinkedField;
import com.k2.dev.model.meta.MetaModel;
import com.k2.dev.model.meta.component.MetaK2LinkedField;
import com.k2.dev.service.K2LinkedFieldService;

@Controller("K2LinkedFieldController")
public class K2LinkedFieldController extends AbstractStatelessEntityController<K2LinkedField> {

    
    @PersistenceUnit
    EntityManagerFactory emf;
    
    @Autowired
    CurrentConversation currentConversation;
    
    @Autowired
    private K2LinkedFieldService service;
    
    @Autowired
    public K2LinkedFieldController(OutputFormatterContextFactory formatterContextFactory, ApplicationContext snippetContext) {
    	super(formatterContextFactory, snippetContext);
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    protected List<MetaField> getMetaFields() { return MetaK2LinkedField.Fields.getFields(); }
    
    @Override
    @SuppressWarnings("rawtypes")
    protected MetaField getMetaField(String parmName) { return  MetaK2LinkedField.Fields.getMetaField(parmName); }
    
    @Override
    @SuppressWarnings("rawtypes")
    protected MetaList getMetaList(String listAlias) { return  MetaK2LinkedField.Lists.getMetaList(listAlias); }
    
    @SuppressWarnings("rawtypes")
    @Override
    protected List<MetaList> getMetaLists() { return MetaK2LinkedField.Lists.getLists(); }
    
    @SuppressWarnings("rawtypes")
    @Override
    protected MetaMethod getMetaMethod(String methodAlias) { return MetaK2LinkedField.Methods.getMetaMethod(methodAlias); }
    
    @SuppressWarnings("rawtypes")
    @Override
    protected List<MetaMethod> getMetaMethods() { return MetaK2LinkedField.Methods.getMethods(); }
    
    @Override
    protected MetaModelEntity getMetaEntity() { return MetaModel.Entities.K2LINKEDFIELD; }
    
    
    @RequestMapping(value="/entities/k2LinkedField", method=RequestMethod.GET)
    public void getEntityList(
    	      RedirectAttributes model,
    		  HttpServletRequest request,
    		  HttpServletResponse response
    		  ) throws IOException {
    	  
    	  ctxManager.get(request);
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin());
    	  
    	  try {
    		  
    		  List<Message> messages = getMessages(model);
    		  
    		  presentEntityList(service.listAll(), MetaK2LinkedField.defaultFieldSet, messages, request, response);
    		  
    	  } finally {
    	  	  conversation.end();
    	  }
    	  
    }
    
    
    @RequestMapping(value="/entities/k2LinkedField", method=RequestMethod.POST)
    public void postEntityList(
    	      @RequestParam(value=ContextManager.PARM_ACTION, required=false) String k2Action,
    	      RedirectAttributes model,
    		  HttpServletRequest request,
    		  HttpServletResponse response
    		  ) throws IOException {
    	  
    	  if (!ctxManager.post(request)) { refresh(response); return; }
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin());
    	  
    	  try {
    	  
    	      if (k2Action != null) {
    	  	      if (k2Action.startsWith("navigate:")) {
    	  	          String destinationURI = k2Action.split(":")[1];
    	  	          navigate(destinationURI, response);
    	  	      
    	  	      } else 
                if (k2Action.startsWith("redirect:")) {
                    String redirect = k2Action.split(":")[1];
                    changeContext(redirect, response);
                    return;
                } else 
                {
    	  		      switch(k2Action) {
    	  		      case "k2:ok":
    	  		  	      goBack(response);
    	  			      break;
    	  		      case "k2:cancel":
    	  			      goBack(response);
    	  			      break;
    	  		      }
    	  	      }
    	      }
    	  
    	  } finally {
    	      conversation.end();
    	  }
    	  
    }
    
    
    @RequestMapping(value="/entities/k2LinkedField/{id}", method=RequestMethod.GET)
    public void getEntity(
    		  @PathVariable("id") Long id, 
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	  
    	  ctxManager.get(request);
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin("get-k2LinkedField").readOnly());
    	  
    	  try {
    		  
    		  List<Message> messages = getMessages(model);
    		  
    		  K2LinkedField entity = service.fetch(id);
    		  if (notFound(id, entity, response)) { return; }
    		  
    		  presentEntity(false, entity, messages, request, response);
    		  
    	  } finally {
    		  conversation.end();
    	  }
    }
    
    
    @RequestMapping(value="/entities/k2LinkedField/{id}", method=RequestMethod.POST)
    public void postEntity(
    		  @RequestParam(value=ContextManager.PARM_ACTION, required=false) String k2Action, 
    		  @PathVariable("id") Long id,
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	  
    	  if (!ctxManager.post(request)) { refresh(response); return; }
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin());
    	  
    	  try {
    	  	  List<Message> messages = getMessages(model);
    	  	  
    	  	  K2LinkedField entity = service.fetch(id);
    	  	  if (notFound(id, entity, response)) { return; }
    	  	  
    	  	  if (k2Action != null) {
    	  		  
    	  		  if (k2Action.startsWith("navigate:")) {
    	  		      String destinationURI = k2Action.split(":")[1];
    	  		      navigate(destinationURI, response);
    	  		      return;
    	  		  } else 
    	  		  if (k2Action.startsWith("redirect:")) {
    	  		      String redirect = k2Action.split(":")[1];
    	  		      changeContext(redirect, response);
    	  		      return;
    	  		  } else 
    	  		  if (k2Action.startsWith("method:")) {
    	  			  
    	  			  updateEntity(entity, messages, request);
    	  			  
    	  			  String methodAlias = k2Action.split(":")[1];
    	  			  @SuppressWarnings("unchecked")
    	  			  MetaMethod<K2LinkedField> metaMethod = MetaK2LinkedField.Methods.getMetaMethod(methodAlias);
    	  			  if (metaMethod.methodParameters.size() == 0) {
    	  				  MethodHandler<K2LinkedField> methodHandler = metaMethod.getHandler(entity);
    	  				  methodHandler.execute();
    	  			  } else {
    	  				  setMessages(model, messages);
    	  				  goGetMethodParms(metaMethod, response);
    	  				  return;
    	  			  }
    	  		  } else {
    	  			  switch(k2Action) {
    	  			  case "k2:cancel":
    	  		  	  	  goBack(response);
    	  				  break;
    	  			  case "k2:ok":
    	  				  updateEntity(entity, messages, request);
    	  		  		  setMessages(model, messages);
    	  		  		  goBack(response);
    	  				  break;
    	  			  case "k2:delete":
    	  				  try {
    	  					  entity.delete();
    	  				  } catch (ValidationException | CascadeDeletionException e) {
    	  					  messages.add(new Message(Message.Level.WARNING, e.getMessage()));
    	  				  }
    	  		  		  setMessages(model, messages);
    	  		  		  goBack(response);
    	  				  break;
    	  			  }
    	  			
    	  		  }
    	  		
    	  	  }
    	  	
    	  	
    	  } finally {
    	  	  conversation.end();
    	  }
    }
    
    
    @RequestMapping(value="/entities/k2LinkedField/new", method=RequestMethod.GET)
    public void getNewEntity(
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	  
    	  ctxManager.get(request);
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin("new-k2LinkedField").readOnly());
    	  
    	  try {
    		  
    		  List<Message> messages = getMessages(model);
    		  
    		  K2LinkedField entity = service.listAll().newBO();
    		  
    		  presentEntity(true, entity, messages, request, response);
    		  
    	  } finally {
    		  conversation.end();
    	  }
    }
    
    
    @RequestMapping(value="/entities/k2LinkedField/new", method=RequestMethod.POST)
    public void postNewEntity(
    		  @RequestParam(value=ContextManager.PARM_ACTION, required=false) String k2Action, 
    		  @RequestParam(value="id", required=true) Long newId,
    		  RedirectAttributes model,
    	      HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	
    	  if (!ctxManager.post(request)) { refresh(response); return; }
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin());
    	  
      	  try {
      		  List<Message> messages = getMessages(model);
      		  
      		  K2LinkedField entity = service.newBO(newId);
      		  if (notFound(newId, entity, response)) { return; }
      		  
      		  if (k2Action != null) {
      		      if (k2Action.startsWith("navigate:")) {
      		          String destinationURI = k2Action.split(":")[1];
      		          navigate(destinationURI, response);
      		          return;
                 } else 
      		      if (k2Action.startsWith("redirect:")) {
      		          String redirect = k2Action.split(":")[1];
      		          changeContext(redirect, response);
      		          return;
      		      } else 
      			  if (k2Action.startsWith("method:")) {
      		  		  updateEntity(entity, messages, request);
      		  	  
      				  String methodAlias = k2Action.split(":")[1];
      				  @SuppressWarnings("unchecked")
      				  MetaMethod<K2LinkedField> metaMethod = MetaK2LinkedField.Methods.getMetaMethod(methodAlias);
      				  if (metaMethod.methodParameters.size() == 0) {
      					  MethodHandler<K2LinkedField> methodHandler = metaMethod.getHandler(entity);
      					  methodHandler.execute();
      				  } else {
      					  setMessages(model, messages);
      					  goGetMethodParms(metaMethod, response);
      					  return;
      				  }
      			  } else {
      				  switch(k2Action) {
      				  case "k2:cancel":
      			  		  goBack(response);
      					  break;
      				  case "k2:ok":
      					  updateEntity(entity, messages, request);
      			  		  setMessages(model, messages);
      			  		  goBack(response);
      					  break;
      				  case "k2:delete":
      					  try {
      						  entity.delete();
      					  } catch (ValidationException | CascadeDeletionException e) {
      						  messages.add(new Message(Message.Level.WARNING, e.getMessage()));
      					  }
      			  		  setMessages(model, messages);
      			  		  goBack(response);
      					  break;
      				  }
      				  
      			  }
      			  
      		  }
      		  
      	  } finally {
      		  conversation.end();
      	  }
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/entities/k2LinkedField/{id}/list/{listAlias}", method=RequestMethod.GET)
    public void getListFromEntity(
    		  @PathVariable("id") Long id, 
    		  @PathVariable("listAlias") String listAlias,
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	  
    	  ctxManager.get(request);
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin());
    	  
    	  try {
    		  
    		  List<Message> messages = getMessages(model);
    		  
    		  K2LinkedField entity = service.fetch(id);
    		  if (notFound(id, entity, response)) { return; }
    		  
    		  MetaList metaList = MetaK2LinkedField.Lists.getMetaList(listAlias);
    		  
    		  presentEntityList(metaList.getHandler(entity).getServiceList(), metaList.fieldSet, messages, request, response);
    		  
    	  } finally {
    		  conversation.end();
    	  }
    	  
    	  return;
    }
    
    
    @RequestMapping(value="/entities/k2LinkedField/{id}/list/{listAlias}", method=RequestMethod.POST)
    public void postListFromEntity(
    		  @RequestParam(value=ContextManager.PARM_ACTION, required=false) String k2Action, 
    		  @PathVariable("id") Long id,
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	  
    	  if (!ctxManager.post(request)) { refresh(response); return; }
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin());
    	  
      	  try {
      		  K2LinkedField entity = service.fetch(id);
      		  if (notFound(id, entity, response)) { return; }
      		  
      		  if (k2Action != null) {
      			  
      			  if (k2Action.startsWith("navigate:")) {
      			      String destinationURI = k2Action.split(":")[1];
      		          navigate(destinationURI, response);
      			      return;
      			  } else 
      		      if (k2Action.startsWith("redirect:")) {
      			      String redirect = k2Action.split(":")[1];
      		          changeContext(redirect, response);
      			      return;
      		      } else {
    			      switch(k2Action) {
    			      case "k2:cancel":
    		  		      goBack(response);
    				      break;
    			      case "k2:ok":
    		  		      goBack(response);
    				      break;
    			      }
    			  }
            }
      		 
        } finally {
      		  conversation.end();
        }
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/entities/k2LinkedField/{id}/list/{listAlias}/new", method=RequestMethod.GET)
    public void getNewEntityInList(
    		  @PathVariable("id") Long id, 
    		  @PathVariable("listAlias") String listAlias,
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	  
    	  ctxManager.get(request);
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin("new-k2LinkedField").readOnly());
    	  
    	  try {
    		  
    		  List<Message> messages = getMessages(model);
    		  
    		  K2LinkedField entity = service.fetch(id);
    		  if (notFound(id, entity, response)) { return; }
    		  
    		  MetaList metaList = MetaK2LinkedField.Lists.getMetaList(listAlias);
    		  
    		  ServiceModel newEntity = (ServiceModel) metaList.getHandler(entity).getServiceList().newBO();
    		  
    		  AbstractStatelessEntityController newEntityController = (AbstractStatelessEntityController) appContext.getBean(metaList.statelessControllerClass);
    		  newEntityController.setContextManager(ctxManager);
    		  newEntityController.presentEntity(true, newEntity, messages, request, response);
    		  
    	  } finally {
    		  conversation.end();
    	  }
    	  return;
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/entities/k2LinkedField/{id}/list/{listAlias}/new", method=RequestMethod.POST)
    public void postNewEntityInList(
    	  @RequestParam(value=ContextManager.PARM_ACTION, required=false) String k2Action, 
    		  @RequestParam(value="id", required=true) Long newId, 
    		  @PathVariable("id") Long id, 
    		  @PathVariable("listAlias") String listAlias,
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	
    	  if (!ctxManager.post(request)) { refresh(response); return; }
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin());
    	  
      try {
    	  List<Message> messages = getMessages(model);
    	
    	  K2LinkedField entity = service.fetch(id);
    	  if (notFound(id, entity, response)) { return; }
    	
    	  MetaList metaList = MetaK2LinkedField.Lists.getMetaList(listAlias);
    	
    	  if (k2Action != null) {
    	      if (k2Action.startsWith("navigate:")) {
    	          String destinationURI = k2Action.split(":")[1];
    	           navigate(destinationURI, response);
    	           return;
    	      } else 
    	      if (k2Action.startsWith("redirect:")) {
    	    	      String redirect = k2Action.split(":")[1];
    	    	      ctxManager.addFlashAttribute(FLASH_ATTR_ENTITY_ID, newId);
    	    	      if (redirect.startsWith("../")) { redirect = redirect.substring(3); }
    	    	      changeContext(redirect, response);
    	    	      return;
    	      } else 
    		  if (k2Action.startsWith("method:")) {
    			
    			  ServiceModel newEntity = (ServiceModel) metaList.getHandler(entity).getServiceList().newBO(newId);
    			  AbstractStatelessEntityController newEntityController = (AbstractStatelessEntityController) appContext.getBean(metaList.statelessControllerClass);
    			  newEntityController.setContextManager(ctxManager);
    			  newEntityController.updateEntity(newEntity, messages, request);
    			
    			  String methodAlias = k2Action.split(":")[1];
    			  MetaMethod metaMethod = MetaModel.getMetaModelEntity(newEntity).metaEntity.getMetaMethod(methodAlias);
    			  if (metaMethod.methodParameters.size() == 0) {
    				  MethodHandler methodHandler = metaMethod.getHandler(newEntity);
    				  methodHandler.execute();
    			  } else {
    				  setMessages(model, messages);
    				  goGetMethodParms(metaMethod, response);
    				  return;
    			  }
    		  } else {
    			  switch(k2Action) {
    			  case "k2:cancel":
    		  		  goBack(response);
    				  break;
    			  case "k2:ok":
    			      ServiceModel newEntity = (ServiceModel) metaList.getHandler(entity).getServiceList().newBO(newId);
    			      AbstractStatelessEntityController newEntityController = (AbstractStatelessEntityController) appContext.getBean(metaList.statelessControllerClass);
    			      newEntityController.setContextManager(ctxManager);
    			      newEntityController.updateEntity(newEntity, messages, request);
       				
    		  		  setMessages(model, messages);
    		  		  goBack(response);
    				  break;
    			  case "k2:delete":
    				  try {
    					  entity.delete();
    				  } catch (ValidationException | CascadeDeletionException e) {
    					  messages.add(new Message(Message.Level.WARNING, e.getMessage()));
    				  }
    		  		  setMessages(model, messages);
    		  		  goBack(response);
    				  break;
    			  }
    			
    		  }
    	    
    	  }
    	
      } finally {
    	  conversation.end();
      }
    
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/entities/k2LinkedField/{id}/list/{listAlias}/{subAlias}/new", method=RequestMethod.GET)
    public void getNewSubEntityInList(
    		  @PathVariable("id") Long id, 
    		  @PathVariable("listAlias") String listAlias,
    		  @PathVariable("subAlias") String subAlias,
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
     		  HttpServletResponse response) throws IOException {
    	
      ctxManager.get(request);
      outputAssembly.setContext(ctxManager);
      Conversation conversation = appContext.getBean(RequestConversation.class);
      currentConversation.set(conversation.begin("new-k2LinkedField").readOnly());
      
      try {
    	  
    	  List<Message> messages = getMessages(model);
    	  
    	  K2LinkedField entity = service.fetch(id);
    	  if (notFound(id, entity, response)) { return; }
    	  
    	  MetaList metaList = MetaK2LinkedField.Lists.getMetaList(listAlias);
    	  
    	  MetaModelEntity mme = MetaModel.getMetaModelEntity(subAlias);
    	  
    	  ServiceModel newEntity = (ServiceModel) metaList.getHandler(entity).getServiceList().newBO();
    	  
    	  try {
    		  ServiceModel subEntity = mme.serviceModelClass.newInstance();
    	   		  subEntity.clone(newEntity);
    	   		
        		  AbstractStatelessEntityController subEntityController = (AbstractStatelessEntityController) appContext.getBean(mme.statelessControllerClass);
        		  subEntityController.setContextManager(ctxManager);
        		  subEntityController.presentEntity(true, subEntity, messages, request, response);
        		  
    	  } catch (InstantiationException | IllegalAccessException e) {
    		  messages.add(new Message(Message.Level.WARNING, e.getMessage()));
    	      e.printStackTrace();
    	  	  setMessages(model, messages);
    	  	  goBack(response);
    	  	  return;
    	  }
    		  
    	  } finally {
    		  conversation.end();
    	  }
    	  return;
    }
     
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/entities/k2LinkedField/{id}/list/{listAlias}/{subAlias}/new", method=RequestMethod.POST)
    public void postNewSubEntityInList(
    	  @RequestParam(value=ContextManager.PARM_ACTION, required=false) String k2Action, 
    		  @RequestParam(value="id", required=true) Long newId, 
    		  @PathVariable("id") Long id, 
    		  @PathVariable("listAlias") String listAlias,
     		  @PathVariable("subAlias") String subAlias,
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    
      if (!ctxManager.post(request)) { refresh(response); return; }
      outputAssembly.setContext(ctxManager);
      Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin());
      
      try {
    	  List<Message> messages = getMessages(model);
    	  
      K2LinkedField entity = service.fetch(id);
      if (notFound(id, entity, response)) { return; }
      
    	  MetaList metaList = MetaK2LinkedField.Lists.getMetaList(listAlias);
      MetaModelEntity mme = MetaModel.getMetaModelEntity(subAlias);
      
    	  if (k2Action != null) {
          if (k2Action.startsWith("navigate:")) {
              String destinationURI = k2Action.split(":")[1];
               navigate(destinationURI, response);
               return;
          } else 
          if (k2Action.startsWith("redirect:")) {
        	      String redirect = k2Action.split(":")[1];
        	      if (redirect.startsWith("../")) { redirect = redirect.substring(3); }
        	      changeContext(redirect, response);
        	      return;
          } else 
    	  if (k2Action.startsWith("method:")) {
    			  
    			  ServiceModel newEntity = (ServiceModel) metaList.getHandler(entity).getServiceList().newBO(newId);
    		  
        		  try {
        			  ServiceModel subEntity = mme.serviceModelClass.newInstance();
        	   		  subEntity.clone(newEntity);
        	   		
            		  AbstractStatelessEntityController subEntityController = (AbstractStatelessEntityController) appContext.getBean(mme.statelessControllerClass);
            		  subEntityController.setContextManager(ctxManager);
            		  subEntityController.updateEntity(subEntity, messages, request);
            		  
        		  } catch (InstantiationException | IllegalAccessException e) {
        			  messages.add(new Message(Message.Level.WARNING, e.getMessage()));
        		      e.printStackTrace();
        		  	  setMessages(model, messages);
        		  	  goBack(response);
        		  	  return;
        		  }
        		  
    			  String methodAlias = k2Action.split(":")[1];
    			  MetaMethod metaMethod = MetaModel.getMetaModelEntity(newEntity).metaEntity.getMetaMethod(methodAlias);
    			  if (metaMethod.methodParameters.size() == 0) {
    				  MethodHandler methodHandler = metaMethod.getHandler(newEntity);
    			  methodHandler.execute();
    		  } else {
    			  setMessages(model, messages);
    				  goGetMethodParms(metaMethod, response);
    				  return;
    			  }
    		  } else {
    			  switch(k2Action) {
    			  case "k2:cancel":
    		  		  goBack(response);
    				  break;
    			  case "k2:ok":
    			      ServiceModel newEntity = (ServiceModel) metaList.getHandler(entity).getServiceList().newBO(newId);
    		      
    		      
            		  try {
            			  ServiceModel subEntity = mme.serviceModelClass.newInstance();
            	   		  subEntity.clone(newEntity);
            	   		
                		  AbstractStatelessEntityController subEntityController = (AbstractStatelessEntityController) appContext.getBean(mme.statelessControllerClass);
                		  subEntityController.setContextManager(ctxManager);
                		  subEntityController.updateEntity(subEntity, messages, request);
                		  
        	    		  } catch (InstantiationException | IllegalAccessException e) {
        	    			  messages.add(new Message(Message.Level.WARNING, e.getMessage()));
        	    		      e.printStackTrace();
        	    		  	  setMessages(model, messages);
        	    		  	  goBack(response);
        	    		  	  return;
        	    		  }
            		  
    		  		  setMessages(model, messages);
    		  		  goBack(response);
    				  break;
    			  case "k2:delete":
    			  try {
    				  entity.delete();
    			  } catch (ValidationException | CascadeDeletionException e) {
    					  messages.add(new Message(Message.Level.WARNING, e.getMessage()));
    				  }
    		  		  setMessages(model, messages);
    		  		  goBack(response);
    				  break;
    			  }
    			  
    		  }
    	      
    	  }
      
      } finally {
    	  conversation.end();
      }
      
     }
     
    
    @SuppressWarnings({ "rawtypes" })
    @RequestMapping(value="/entities/k2LinkedField/{id}/method/{methodAlias}", method=RequestMethod.GET)
    public void getMethodParameters(
    		  @PathVariable("id") Long id, 
    		  @PathVariable("methodAlias") String methodAlias,
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	  
    	  ctxManager.get(request);
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin("new-k2LinkedField").readOnly());
    	  
    	  try {
    		  
    		  List<Message> messages = getMessages(model);
    		  
    		  K2LinkedField entity = service.fetch(id);
    		  if (notFound(id, entity, response)) { return; }
    		  
    		  MetaMethod metaMethod = MetaK2LinkedField.Methods.getMetaMethod(methodAlias);
    		  
    		  presentMethod(entity, metaMethod, messages, request, response);
    		  
    	  } finally {
    		  conversation.end();
    	  }
    }
    
    
    @SuppressWarnings({ "rawtypes" })
    @RequestMapping(value="/entities/k2LinkedField/{id}/method/{methodAlias}", method=RequestMethod.POST)
    public void postMethodParameters(
    		  @PathVariable("id") Long id, 
    		  @PathVariable("methodAlias") String methodAlias,
    		  RedirectAttributes model,
    		  HttpServletRequest request, 
    		  HttpServletResponse response) throws IOException {
    	  
    	  if (!ctxManager.post(request)) { refresh(response); return; }
    	  outputAssembly.setContext(ctxManager);
    	  Conversation conversation = appContext.getBean(RequestConversation.class);
    	  currentConversation.set(conversation.begin("new-k2LinkedField").readOnly());
    	  
    	  try {
    		  
    		  List<Message> messages = getMessages(model);
    		  
    		  K2LinkedField entity = service.fetch(id);
    		  if (notFound(id, entity, response)) { return; }
    		  
    		  MetaMethod metaMethod = MetaK2LinkedField.Methods.getMetaMethod(methodAlias);
    		  
    		  setMessages(model, messages);
    		  
    		  executeMethod(entity, metaMethod, request, response);
    		  
    		  goBack(response);
    		  
    	  } finally {
    		  conversation.end();
    	  }
    }
    
}
