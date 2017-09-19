package com.k2.common.util;

import java.util.Date;

public class DateUtil {
	
	public static Integer toIntger(Date v) {
		if (v == null) { return null; }
		return new Long(v.getTime()).intValue();
	}
	
	public static Long toLong(Date v) {
		if (v == null) { return null; }
		return v.getTime();
	}
	
	public static Float toFloat(Date v) {
		if (v == null) { return null; }
		return new Float(v.getTime());
	}
	
	public static Double toDouble(Date v) {
		if (v == null) { return null; }
		return new Double(v.getTime()); 	
	}
	
	public static Boolean toBoolean(Date v) {
		if (v == null) { return null; }
		if (v.getTime() == 0) { return false; }
		return true;	
	}
	
	public static Date toDate(Date v) {
		return v;
	}
	
	public static String toString(Date v) {
		if (v == null) { return null; }
		return UtilityFactory.k2Utils().getDateFormatter().format(v);
	}
	

}
