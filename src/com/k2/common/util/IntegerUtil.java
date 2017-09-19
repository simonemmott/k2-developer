package com.k2.common.util;

import java.util.Date;

public class IntegerUtil {
	
	public static Integer toIntger(Integer v) {
		return v;
	}
	
	public static Long toLong(Integer v) {
		if (v == null) { return null; }
		return new Long(v);
	}
	
	public static Float toFloat(Integer v) {
		if (v == null) { return null; }
		return new Float(v);
	}
	
	public static Double toDouble(Integer v) {
		if (v == null) { return null; }
		return new Double(v); 	
	}
	
	public static Boolean toBoolean(Integer v) {
		if (v == null) { return null; }
		if (v.equals(0)) { return false; }
		return true;	
	}
	
	public static Date toDate(Integer v) {
		if (v == null) { return null; }
		return new Date(v);
	}
	
	public static String toString(Integer v) {
		if (v == null) { return null; }
		return v.toString();
	}
	

}
