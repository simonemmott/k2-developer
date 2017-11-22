package com.k2.dev.model.serialisation.template;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.serialisation.GenericSerialiserScheme;
import com.k2.common.serialisation.SerialiserScheme;
import com.k2.common.service.ServiceList;
import com.k2.common.writeEvents.ValidationException;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.Template;
import com.k2.dev.model.TemplateBinding;
import com.k2.dev.model.entity.TemplateENT;
import com.k2.dev.service.TemplateService;

@Configurable
public class Default extends GenericSerialiserScheme<Template> implements SerialiserScheme<Template> {
	
	@Autowired(required=true)
	TemplateService service;
	
	@Override
	public void serialize() throws IOException {
		
		writer.beginObject();
		if ((entity != null)&&(!entity.isNull())) {
			writer.name("id").value(entity.getId());
			writer.name("name").value(entity.getName());
			writer.name("className").value(entity.getClassName());
			writer.name("description").value(entity.getDescription());
			writer.name("packageName").value(entity.getPackageName());
			writer.name("populatesSnippet");
			new com.k2.dev.model.serialisation.k2Snippet.Default().setWriter(writer).setEntity(entity.getPopulatesSnippet()).serialize();
			
			ServiceList<K2SnippetContainer> containers = entity.getContainers();
			writer.name("containers").beginArray();
			while (containers.hasNext()) { new com.k2.dev.model.serialisation.k2SnippetContainer.Default().setWriter(writer).setEntity(containers.next()).serialize(); }
			writer.endArray();
			
			ServiceList<K2SnippetParameter> parameters = entity.getParameters();
			writer.name("parameters").beginArray();
			while (parameters.hasNext()) { new com.k2.dev.model.serialisation.k2SnippetParameter.Default().setWriter(writer).setEntity(parameters.next()).serialize(); }
			writer.endArray();
			
			ServiceList<TemplateBinding> templateBindings = entity.getTemplateBindings();
			writer.name("bindings").beginArray();
			while (templateBindings.hasNext()) { new com.k2.dev.model.serialisation.templateBinding.Default().setWriter(writer).setEntity(templateBindings.next()).serialize(); }
			writer.endArray();
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
					entity = service.getBO(new TemplateENT());
				}
				entity.setId(id);
				break;
			case "name":
				entity.setName(reader.nextString());
				break;
			case "className":
				entity.setClassName(reader.nextString());
				break;
			case "description":
				entity.setDescription(reader.nextString());
				break;
			case "packageName":
				entity.setPackageName(reader.nextString());
				break;
			case "populatesSnippet":
				entity.write();
				new com.k2.dev.model.serialisation.k2Snippet.Default().setReader(reader).marshall();
				break;
			case "containers":
				entity.write();
				reader.beginArray();
				while (reader.hasNext()) {
					new com.k2.dev.model.serialisation.k2SnippetContainer.Default().setReader(reader).marshall();
				}
				reader.endArray();
				break;
			case "parameters":
				entity.write();
				reader.beginArray();
				while (reader.hasNext()) {
					new com.k2.dev.model.serialisation.k2SnippetParameter.Default().setReader(reader).marshall();
				}
				reader.endArray();
				break;
			case "bindings":
				entity.write();
				reader.beginArray();
				while (reader.hasNext()) {
					new com.k2.dev.model.serialisation.templateBinding.Default().setReader(reader).marshall();
				}
				reader.endArray();
				break;
			default:
				reader.skipValue();
			}
		}
		reader.endObject();
		entity.write();
	}

}
