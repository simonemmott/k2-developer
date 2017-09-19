package com.k2.common.util;

import java.util.HashSet;
import java.util.Set;

public class SetUtil {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set toSet(Object ... entities) {
		Set set = new HashSet();
		for (Object e : entities) {
			set.add(e);
		}
		return set;
	}

}
