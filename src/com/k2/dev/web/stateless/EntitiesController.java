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

import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContext;
import com.k2.common.entityOutputFormatter.outputFormatterContext.OutputFormatterContextFactory;
import com.k2.common.meta.MetaEntity;
import com.k2.common.snippets.SnippetFactory;
import com.k2.common.snippets.html.HtmlPage;
import com.k2.common.snippets.html.HtmlButton;
import com.k2.common.snippets.html.HtmlHeader;
import com.k2.common.snippets.html.HtmlInline;
import com.k2.common.snippets.html.HtmlLabel;
import com.k2.dev.model.TemplateContent;
import com.k2.dev.model.meta.MetaModel;

@Controller
public class EntitiesController {
	
	private OutputFormatterContext<TemplateContent, PrintWriter> context;
	
	@SuppressWarnings("unchecked")
	@Autowired
	public EntitiesController(OutputFormatterContextFactory formatterContextFactory, ApplicationContext snippetContext) {
		context = (OutputFormatterContext<TemplateContent, PrintWriter>)formatterContextFactory.getContext();
		context.setSnippetFactory(snippetContext.getBean(SnippetFactory.class));
		context.setVerbose();
	}
	
	
	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping(value="/entities", method=RequestMethod.GET)
	public String getEntities(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html");
		
		HtmlPage<PrintWriter> page = context.getSnippet(HtmlPage.class);
		page.addStyleSheets("css/k2-default.css");
		
		{
			HtmlHeader<PrintWriter> title = context.getSnippet(HtmlHeader.class);
			title.setText("List of Entities");
			title.setLevel(1);
		
			page.addToBody(title);
		}
		
		for (MetaEntity entity : MetaModel.getEntities()) {
			HtmlInline<PrintWriter> inline = context.getSnippet(HtmlInline.class);
			{
				HtmlLabel<PrintWriter> entityLabel = context.getSnippet(HtmlLabel.class);
				entityLabel.setLabel(entity.name);
				inline.addToBody(entityLabel);
			}
			{
				HtmlButton<PrintWriter> button = context.getSnippet(HtmlButton.class);
				button.setLabel("Show");
				button.setAction("entities/"+entity.alias);
				inline.addToBody(button);
			}
			page.addToBody(inline);
		}
		
		try {
			page.output(response.getWriter());
		} catch (IOException e) {
			response.reset();
			try {
				response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
