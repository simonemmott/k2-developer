package com.k2.dev.web.stateless;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.interaction.ContextManager;
import com.k2.common.interaction.Conversation;
import com.k2.common.interaction.CurrentConversation;
import com.k2.common.interaction.RequestConversation;
import com.k2.common.meta.MetaModelEntity;
import com.k2.common.meta.MetaMethod;
import com.k2.common.snippets.SnippetFactory;
import com.k2.common.snippets.html.HtmlPage;
import com.k2.common.snippets.html.HtmlButton;
import com.k2.common.snippets.html.HtmlForm;
import com.k2.common.snippets.html.HtmlHeader;
import com.k2.common.snippets.html.HtmlInline;
import com.k2.common.snippets.html.HtmlLabel;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.meta.MetaModel;

@Controller
public class EntitiesController {
	
	@Autowired
	protected ContextManager ctxManager;
	  
	@Autowired
	protected ApplicationContext appContext;

	@Autowired
	CurrentConversation currentConversation;

	private OutputFormatterContext<TemplateContent, PrintWriter> outputAssembly;
	
	@SuppressWarnings("unchecked")
	@Autowired
	public EntitiesController(OutputFormatterContextFactory formatterContextFactory, ApplicationContext snippetContext) {
		outputAssembly = (OutputFormatterContext<TemplateContent, PrintWriter>)formatterContextFactory.getContext();
		outputAssembly.setSnippetFactory(snippetContext.getBean(SnippetFactory.class));
		outputAssembly.setVerbose();
	}
	
	protected String contextQuery() { 
		if (ctxManager==null) 
		{ 
			return ""; 
		} else { 
			return ContextManager.PARM_CONTEXT_ID+"="+ctxManager.getContextId(); 
		}
	}
	
	protected void refresh(HttpServletResponse response) throws IOException {
		redirect(ctxManager.getContextPath()+ctxManager.getPathInfo(), response);
	}
	
	protected void navigate(String destinationURI, HttpServletResponse response) throws IOException {
		redirect(destinationURI, response);
	}
	
	protected void redirect(String redirect, HttpServletResponse response) throws IOException {
		response.sendRedirect(redirect);
	}
	
	protected void goBack(HttpServletResponse response) throws IOException {
		redirect(ctxManager.goBack(), response);
	}

	
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="/entities", method=RequestMethod.GET)
	public void getEntities(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ctxManager.get(request);
		outputAssembly.setContext(ctxManager);
	  	Conversation conversation = appContext.getBean(RequestConversation.class);
	  	currentConversation.set(conversation.begin());

	  	try {
		
			response.setContentType("text/html");
			
			HtmlPage<PrintWriter> page = outputAssembly.getSnippet(HtmlPage.class);
			page.addStyleSheets("css/k2-default.css");
			
			{
				HtmlHeader<PrintWriter> title = outputAssembly.getSnippet(HtmlHeader.class);
				title.setText("List of Entities");
				title.setLevel(1);
			
				page.addToBody(title);
			}
			{
				HtmlForm<PrintWriter> form = outputAssembly.getSnippet(HtmlForm.class);
				page.addToBody(form);

				for (MetaModelEntity entity : MetaModel.getEntities()) {
					HtmlInline<PrintWriter> inline = outputAssembly.getSnippet(HtmlInline.class);
					{
						HtmlLabel<PrintWriter> entityLabel = outputAssembly.getSnippet(HtmlLabel.class);
						entityLabel.setLabel(entity.name);
						inline.addToBody(entityLabel);
					}
					{
						HtmlButton<PrintWriter> button = outputAssembly.getSnippet(HtmlButton.class);
						button.setLabel("Show");
						button.setButtonType("submit");
						button.setName("k2:action");
						button.setValue("navigate:entities/"+entity.alias);
						inline.addToBody(button);
					}
					form.addToBody(inline);
				}
			}

			page.output(response.getWriter());
	  	} finally {
	  		conversation.end();
	  	}
	}

	@RequestMapping(value="/entities", method=RequestMethod.POST)
	public void postEntities(
			@RequestParam(value="k2:action", required=false) String k2Action, 
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
	  				
	  			} else {  			
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

}
