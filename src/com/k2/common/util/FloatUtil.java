package com.k2.common.util;

import java.util.Date;

public class FloatUtil {
	
	public static Integer toIntger(Float v) {
		if (v == null) { return null; }
		return v.intValue();
	}
	
	public static Long toLong(Float v) {
		if (v == null) { return null; }
		return v.longValue();
	}
	
	public static Float toFloat(Float v) {
		if (v == null) { return null; }
		return v;
	}
	
	public static Double toDouble(Float v) {
		if (v == null) { return null; }
		return new Double(v); 	
	}
	
	public static Boolean toBoolean(Float v) {
		if (v == null) { return null; }
		if (v.equals(0)) { return false; }
		return true;	
	}
	
	public static Date toDate(Float v) {
		if (v == null) { return null; }
		return new Date(v.longValue());
	}
	
	public static String toString(Float v) {
		if (v == null) { return null; }
		return v.toString();
	}
	

}
