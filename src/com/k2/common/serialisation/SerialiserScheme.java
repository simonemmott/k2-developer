package com.k2.common.serialisation;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.k2.common.writeEvents.ValidationException;

public interface SerialiserScheme<E> {
	
	public SerialiserScheme<E> setEntity(E entity);
	public SerialiserScheme<E> setWriter(JsonWriter writer);
	
	public void serialize() throws IOException;
	public void marshall() throws IOException, ValidationException;
	public SerialiserScheme<E> setReader(JsonReader reader);

}
