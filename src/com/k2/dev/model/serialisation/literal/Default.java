package com.k2.dev.model.serialisation.literal;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.k2.common.serialisation.GenericSerialiserScheme;
import com.k2.common.serialisation.SerialiserScheme;
import com.k2.common.util.DateUtil;
import com.k2.common.writeEvents.ValidationException;
import com.k2.dev.model.Literal;
import com.k2.dev.model.entity.LiteralENT;
import com.k2.dev.service.LiteralService;
import com.k2.common.util.StringUtil;

@Configurable
public class Default extends GenericSerialiserScheme<Literal> implements SerialiserScheme<Literal> {
	
	@Autowired(required=true)
	LiteralService service;
	
	@Override
	public void serialize() throws IOException {
		
		writer.beginObject();
		if ((entity != null)&&(!entity.isNull())) {
			writer.name("id").value(entity.getId());
			writer.name("dataType").value(entity.getDataType().name());
			writer.name("textValue").value(entity.getTextValue());
			writer.name("booleanValue").value(entity.getBooleanValue());
			writer.name("decimalValue").value(entity.getDecimalValue());
			writer.name("numericValue").value(entity.getNumericValue());
			writer.name("dateValue").value(DateUtil.toString(entity.getDateValue()));
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
					entity = service.getBO(new LiteralENT());
				}
				entity.setId(id);
				break;
			case "dataType":
				entity.setDataType(LiteralENT.Types.LiteralDataType.valueOf(reader.nextString()));
				break;
			case "textValue":
				entity.setTextValue(reader.nextString());
				break;
			case "booleanValue":
				entity.setBooleanValue(reader.nextBoolean());
				break;
			case "decimalValue":
				entity.setDecimalValue(reader.nextDouble());
				break;
			case "numericValue":
				entity.setNumericValue(reader.nextLong());
				break;
			case "dateValue":
				entity.setDateValue(StringUtil.toDate(reader.nextString()));
				break;
			default:
				reader.skipValue();
			}
		}
		reader.endObject();
		entity.write();
	}
}
