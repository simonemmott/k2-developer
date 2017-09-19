package com.k2.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(Object ... entities) {
		List list = new ArrayList();
		for (Object e : entities) {
			list.add(e);
		}
		return list;
	}

}
