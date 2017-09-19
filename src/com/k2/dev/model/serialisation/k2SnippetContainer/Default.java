package com.k2.dev.model.serialisation.k2SnippetContainer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.serialisation.GenericSerialiserScheme;
import com.k2.common.serialisation.SerialiserScheme;
import com.k2.common.writeEvents.ValidationException;
import com.k2.dev.model.K2SnippetContainer;
import com.k2.dev.model.entity.K2SnippetContainerENT;
import com.k2.dev.service.K2SnippetContainerService;

@Configurable
public class Default extends GenericSerialiserScheme<K2SnippetContainer> implements SerialiserScheme<K2SnippetContainer> {
	
	@Autowired(required=true)
	K2SnippetContainerService service;
	
	@Override
	public void serialize() throws IOException {
		
		writer.beginObject();
		if ((entity != null)&&(!entity.isNull())) {
			writer.name("id").value(entity.getID());
			writer.name("name").value(entity.getName());
			writer.name("description").value(entity.getDescription());
		
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
					entity = service.getBO(new K2SnippetContainerENT());
				}
				entity.setID(id);
				break;
			case "name":
				entity.setName(reader.nextString());
				break;
			case "description":
				entity.setDescription(reader.nextString());
				break;
			default:
				reader.skipValue();
			}
		}
		reader.endObject();
		entity.write();
	}

}
