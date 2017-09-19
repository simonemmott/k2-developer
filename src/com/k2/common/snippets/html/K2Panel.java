package com.k2.common.snippets.html;

import com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet;

public interface K2Panel<O> extends OutputSnippet<O> {

	K2Panel<O> setTitle(String v);

	String getTitle();

	K2Panel<O> setWidth(Integer v);

	Integer getWidth();

	K2Panel<O> setHeight(Integer v);

	Integer getHeight();

	K2Panel<O> addToTitle(OutputSnippet<O> snippet);

	K2Panel<O> addToBody(OutputSnippet<O> snippet);

	O output(O output);

}