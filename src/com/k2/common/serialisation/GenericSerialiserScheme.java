package com.k2.common.serialisation;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.k2.common.writeEvents.ValidationException;

public abstract class GenericSerialiserScheme<E> implements SerialiserScheme<E>{
	
	protected E entity;
	@Override
	public SerialiserScheme<E> setEntity(E entity) { this.entity = entity; return this; }

	protected JsonWriter writer;
	@Override
	public SerialiserScheme<E> setWriter(JsonWriter writer) { this.writer = writer; return this; }
	
	protected JsonReader reader;
	@Override
	public SerialiserScheme<E> setReader(JsonReader reader) { this.reader = reader; return this; }

	@Override
	public abstract void serialize() throws IOException;
	
	@Override
	public abstract void marshall() throws IOException, ValidationException;



}
