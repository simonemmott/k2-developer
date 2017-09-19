package com.k2.common.parameters;

import java.util.ArrayList;
import java.util.List;

public class K2Parameters {
	public static final String K2_SCAN_ENTITIES="k2.scan.entities";
	public static final String K2_SCAN_COMPONENTS="k2.scan.components";
	public static final String K2_SNIPPETS="k2.snippets";
	public static final String K2_SNIPPET_CONFIG="k2.snippet.config";

	public static final List<String> propertyList() {
		List<String> propertyList = new ArrayList<String>();
		propertyList.add(K2_SCAN_ENTITIES);
		propertyList.add(K2_SCAN_COMPONENTS);
		propertyList.add(K2_SNIPPETS);
		propertyList.add(K2_SNIPPET_CONFIG);
		return propertyList;
	}
}
