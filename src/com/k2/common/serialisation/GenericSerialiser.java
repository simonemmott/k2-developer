package com.k2.common.serialisation;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;
import org.springframework.transaction.annotation.Transactional;


public class GenericSerialiser<E> implements Serialiser<E> {

	@Override
	@Transactional
	public void serialise(E entity, Class<? extends SerialiserScheme<E>> schemeClass, JsonWriter writer) throws IOException {
		try {
			SerialiserScheme<E> scheme = schemeClass.newInstance();
			scheme.setWriter(writer);
			scheme.setEntity(entity);
			scheme.serialize();
			
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
