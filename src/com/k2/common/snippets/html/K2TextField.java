package com.k2.common.snippets.html;

import com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet;

public interface K2TextField<O> extends OutputSnippet<O> {

	K2TextField<O> setLabel(String v);

	String getLabel();

	K2TextField<O> setLength(Integer v);

	Integer getLength();

	K2TextField<O> setRequired(Boolean v);

	Boolean getRequired();

	O output(O output);

}