package com.k2.dev.model.serialisation.templateBinding;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.serialisation.GenericSerialiserScheme;
import com.k2.common.serialisation.SerialiserScheme;
import com.k2.common.writeEvents.ValidationException;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.entity.TemplateBindingENT;
import com.k2.dev.service.TemplateBindingService;

@Configurable
public class Default extends GenericSerialiserScheme<TemplateBinding> implements SerialiserScheme<TemplateBinding> {
	
	@Autowired(required=true)
	TemplateBindingService service;
	
	@Override
	public void serialize() throws IOException {
		
		writer.beginObject();
		if ((entity != null)&&(!entity.isNull())) {
			writer.name("id").value(entity.getId());
			writer.name("bindingParameter");
			new com.k2.dev.model.serialisation.k2SnippetParameter.Default().setWriter(writer).setEntity(entity.getBindingParameter()).serialize();
			
			writer.name("bindingTemplate");
			new com.k2.dev.model.serialisation.template.Default().setWriter(writer).setEntity(entity.getBindingTemplate()).serialize();
			
			writer.name("bindingSource").value(entity.getBindingSource().getName());
			
			writer.name("boundParameter");
			new com.k2.dev.model.serialisation.k2SnippetParameter.Default().setWriter(writer).setEntity(entity.getBoundParameter()).serialize();
			
			writer.name("literal");
			new com.k2.dev.model.serialisation.literal.Default().setWriter(writer).setEntity(entity.getLiteral()).serialize();
			
			writer.name("widget");
			new com.k2.dev.model.serialisation.k2Snippet.Default().setWriter(writer).setEntity(entity.getWidget()).serialize();
		}
		writer.endObject();
		
	}

	@Override
	public void marshall() throws IOException, ValidationException {
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			switch (name) {
			case "id":
				Long id = reader.nextLong();
				entity = service.fetch(id);
				if (entity.isNull()) {
					entity = service.getBO(new TemplateBindingENT());
				}
				entity.setId(id);
				break;
			case "bindingSource":
				entity.setBindingSource(TemplateBindingENT.Types.BindingSource.valueOf(reader.nextString()));
				break;
			case "bindingParameter":
				new com.k2.dev.model.serialisation.k2SnippetParameter.Default().setReader(reader).marshall();
				break;
			case "bindingTemplate":
				new com.k2.dev.model.serialisation.template.Default().setReader(reader).marshall();
				break;
			case "boundParameter":
				new com.k2.dev.model.serialisation.k2SnippetParameter.Default().setReader(reader).marshall();
				break;
			case "literal":
				new com.k2.dev.model.serialisation.literal.Default().setReader(reader).marshall();
				break;
			case "widget":
				new com.k2.dev.model.serialisation.k2Snippet.Default().setReader(reader).marshall();
				break;
			default:
				reader.skipValue();
			}
		}
		reader.endObject();
		entity.write();
	}
}
