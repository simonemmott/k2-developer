package com.k2.dev.model.serialisation.k2Field;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.serialisation.GenericSerialiserScheme;
import com.k2.common.serialisation.SerialiserScheme;
import com.k2.common.writeEvents.ValidationException;
import com.k2.dev.model.K2Field;
import com.k2.dev.model.entity.K2FieldENT;
import com.k2.dev.service.K2FieldService;

@Configurable
public class Default extends GenericSerialiserScheme<K2Field> implements SerialiserScheme<K2Field> {

	@Autowired(required=true)
	K2FieldService service;
	
	@Override
	public void serialize() throws IOException {
		
		writer.beginObject();
		if ((entity != null)&&(!entity.isNull())) {
			writer.name("id").value(entity.getID());
			writer.name("name").value(entity.getName());
			writer.name("columnLength").value(entity.getColumnLength());
			writer.name("dataType").value(entity.getDataType());
			writer.name("columnName").value(entity.getColumnName());
			writer.name("nullable").value(entity.getNullable());
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
					entity = service.getBO(new K2FieldENT());
				}
				entity.setID(id);
				break;
			case "name":
				entity.setName(reader.nextString());
				break;
			case "columnLength":
				entity.setColumnLength(reader.nextInt());
				break;
			case "dataType":
				entity.setDataType(reader.nextString());
				break;
			case "columnName":
				entity.setColumnName(reader.nextString());
				break;
			case "nullable":
				entity.setNullable(reader.nextBoolean());
				break;
			default:
				reader.skipValue();
			}
		}
		reader.endObject();
		entity.write();
		
	}

}
