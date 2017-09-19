package com.k2.common.util;

import java.util.Date;

public class BooleanUtil {
	
	public static Integer toIntger(Boolean v) {
		if (v == null) { return null; }
		if (v) { return 1; } else { return 0; }
	}
	
	public static Long toLong(Boolean v) {
		if (v == null) { return null; }
		if (v) { return 1L; } else { return 0L; }
	}
	
	public static Float toFloat(Boolean v) {
		if (v == null) { return null; }
		if (v) { return new Float(1); } else { return new Float(0); }
	}
	
	public static Double toDouble(Boolean v) {
		if (v == null) { return null; }
		if (v) { return new Double(1); } else { return new Double(0); }		
	}
	
	public static Boolean toBoolean(Boolean v) {
		return v;		
	}
	
	public static Date toDate(Boolean v) {
		if (v == null) { return null; }
		if (v) { return new Date(); } else { return new Date(0); }
	}
	
	public static String toString(Boolean v) {
		if (v == null) { return null; }
		if (v) { return "true"; } else { return "false"; }
	}
	

}
