package com.k2.common.util;

import java.util.Date;

public class DoubleUtil {
	
	public static Integer toIntger(Double v) {
		if (v == null) { return null; }
		return v.intValue();
	}
	
	public static Long toLong(Double v) {
		if (v == null) { return null; }
		return v.longValue();
	}
	
	public static Float toFloat(Double v) {
		if (v == null) { return null; }
		return new Float(v);
	}
	
	public static Double toDouble(Double v) {
		return v; 	
	}
	
	public static Boolean toBoolean(Double v) {
		if (v == null) { return null; }
		if (v.equals(0)) { return false; }
		return true;	
	}
	
	public static Date toDate(Double v) {
		if (v == null) { return null; }
		return new Date(v.longValue());
	}
	
	public static String toString(Double v) {
		if (v == null) { return null; }
		return v.toString();
	}
	

}
