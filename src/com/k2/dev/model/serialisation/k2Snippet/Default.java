package com.k2.dev.model.serialisation.k2Snippet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.serialisation.GenericSerialiserScheme;
import com.k2.common.serialisation.SerialiserScheme;
import com.k2.common.service.ServiceList;
import com.k2.common.writeEvents.ValidationException;
import com.k2.dev.model.K2Snippet;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.K2SnippetParameter;
import com.k2.dev.model.entity.K2SnippetENT;
import com.k2.dev.service.K2SnippetService;

@Configurable
public class Default extends GenericSerialiserScheme<K2Snippet> implements SerialiserScheme<K2Snippet> {
	
	@Autowired(required=true)
	K2SnippetService service;
	
	@Override
	public void serialize() throws IOException {
		
		writer.beginObject();
		if ((entity != null)&&(!entity.isNull())) {
			writer.name("id").value(entity.getID());
			writer.name("name").value(entity.getName());
			writer.name("className").value(entity.getClassName());
			writer.name("description").value(entity.getDescription());
			writer.name("packageName").value(entity.getPackageName());
			
			ServiceList<K2SnippetContainer> containers = entity.getContainers();
			writer.name("containers").beginArray();
			while (containers.hasNext()) { new com.k2.dev.model.serialisation.k2SnippetContainer.Default().setWriter(writer).setEntity(containers.next()).serialize(); }
			writer.endArray();
			
			ServiceList<K2SnippetParameter> parameters = entity.getParameters();
			writer.name("parameters").beginArray();
			while (parameters.hasNext()) { new com.k2.dev.model.serialisation.k2SnippetParameter.Default().setWriter(writer).setEntity(parameters.next()).serialize(); }
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
					entity = service.getBO(new K2SnippetENT());
				}
				entity.getEntity().setID(id);
				break;
			case "name":
				entity.getEntity().setName(reader.nextString());
				break;
			case "className":
				entity.getEntity().setClassName(reader.nextString());
				break;
			case "description":
				entity.getEntity().setDescription(reader.nextString());
				break;
			case "packageName":
				entity.setPackageName(reader.nextString());
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
			default:
				reader.skipValue();
			}
		}
		reader.endObject();
		entity.write();
		
	}

}
