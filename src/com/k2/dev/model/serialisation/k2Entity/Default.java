package com.k2.dev.model.serialisation.k2Entity;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.serialisation.GenericSerialiserScheme;
import com.k2.common.serialisation.SerialiserScheme;
import com.k2.common.service.ServiceList;
import com.k2.common.writeEvents.ValidationException;
import com.k2.dev.model.K2Entity;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.entity.K2EntityENT;
import com.k2.dev.service.K2EntityService;

@Configurable
public class Default extends GenericSerialiserScheme<K2Entity> implements SerialiserScheme<K2Entity> {
	
	@Autowired(required=true)
	K2EntityService service;
	
	@Override
	public void serialize() throws IOException {
		
		writer.beginObject();
		if ((entity != null)&&(!entity.isNull())) {
			writer.name("id").value(entity.getId());
			writer.name("packageName").value(entity.getPackageName());
			writer.name("name").value(entity.getName());
			writer.name("entityName").value(entity.getEntityName());
			writer.name("tableName").value(entity.getTableName());
			writer.name("inheritanceJoinColumn").value(entity.getInheritanceJoinColumn());
			
			writer.name("extendsEntity");
			new com.k2.dev.model.serialisation.k2Entity.Default().setWriter(writer).setEntity(entity.getExtendsEntity()).serialize();
			
			ServiceList<K2Field> fields = entity.getFields();
			writer.name("fields").beginArray();
			while (fields.hasNext()) { new com.k2.dev.model.serialisation.k2Field.Default().setWriter(writer).setEntity(fields.next()).serialize(); }
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
					entity = service.getBO(new K2EntityENT());
				}
				entity.setId(id);
				break;
			case "packageName":
				entity.setPackageName(reader.nextString());
				break;
			case "name":
				entity.setName(reader.nextString());
				break;
			case "entityName":
				entity.setEntityName(reader.nextString());
				break;
			case "tableName":
				entity.setTableName(reader.nextString());
				break;
			case "inheritanceJoinColumn":
				entity.setInheritanceJoinColumn(reader.nextString());
				break;
			case "fields":
				entity.write();
				reader.beginArray();
				while (reader.hasNext()) {
					new com.k2.dev.model.serialisation.k2Field.Default().setReader(reader).marshall();
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
