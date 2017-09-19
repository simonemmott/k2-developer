package com.k2.common.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

public class StringUtil {
	
	public static Integer toInteger(String v) {
		if (v == null) { return null; }
		try {
			return Integer.parseInt(v);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Long toLong(String v) {
		if (v == null) { return null; }
		try {
			return Long.parseLong(v);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Float toFloat(String v) {
		if (v == null) { return null; }
		try {
			return Float.parseFloat(v);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Double toDouble(String v) {
		if (v == null) { return null; }
		try {
			return Double.parseDouble(v);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean toBoolean(String v) {
		if (v == null) { return null; }
		if (v.equals("")) { return false; }
		if (v.equalsIgnoreCase("true")) { return true; }
		if (v.equalsIgnoreCase("false")) { return false; }
		if (StringUtil.toInteger(v).equals(0)) { return false; }
		return true;	
	}
	
	public static Date toDate(String v) {
		if (v == null) { return null; }
		try {
			return UtilityFactory.k2Utils().getDateFormatter().parse(v);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String toString(String v) {
		return v;
	}
	
	public static String randomString(int length) {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMONPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
	
	public static String initialLowerCase(String input) {
		String c = input.substring(0, 1).toLowerCase();
		return c+input.substring(1);
	}

	public static String initialUpperCase(String input) {
		String c = input.substring(0, 1).toUpperCase();
		return c+input.substring(1);
	}


}
