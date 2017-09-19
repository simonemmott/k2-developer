package com.k2.common.snippets.html;

import com.k2.common.entityOutputFormatter.outputSnippet.OutputSnippet;

public interface K2HTMLPage<O> {

	public K2HTMLPage<O> addStyleSheets(String... styles);

	public K2HTMLPage<O> addScripts(String... scripts);

	public K2HTMLPage<O> addKeywords(String... words);

	public K2HTMLPage<O> setTitle(String v);

	public String getTitle();

	public K2HTMLPage<O> addToBody(OutputSnippet<O> snippet);

	public O output(O output);

}