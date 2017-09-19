package com.k2.common.util;

import com.k2.common.util.impl.K2UtilitiesImpl;

public class UtilityFactory {
	
	public static K2Utilities k2Utils() {
		return new K2UtilitiesImpl();
	}
	
	public static BooleanUtil booleanUtils() {
		return new BooleanUtil();
	}
	
	public static ClassUtil classUtils() {
		return new ClassUtil();
	}
	
	public static DateUtil dateUtils() {
		return new DateUtil();
	}
	
	public static EntityUtil doubleUtils() {
		return new EntityUtil();
	}
	
	public static FloatUtil floatUtils() {
		return new FloatUtil();
	}
	
	public static IntegerUtil integerUtils() {
		return new IntegerUtil();
	}
	
	public static ListUtil listUtils() {
		return new ListUtil();
	}
	
	public static LongUtil longUtils() {
		return new LongUtil();
	}
	
	public static SetUtil setUtils() {
		return new SetUtil();
	}
	
	public static StringUtil stringUtils() {
		return new StringUtil();
	}
	


}
