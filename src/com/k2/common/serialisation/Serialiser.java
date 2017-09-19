package com.k2.common.serialisation;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

public interface Serialiser<E> {
	
	public void serialise(E entity, Class<? extends SerialiserScheme<E>> schemeClass, JsonWriter writer) throws IOException;

	
}
