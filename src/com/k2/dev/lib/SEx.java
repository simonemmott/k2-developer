package com.k2.dev.lib;

import com.k2.common.expressions.Ex;

public class SEx {

	public static final Integer test() {
		return Ex.INTEGER.add(
			Literals.ten,
			test2(
				Ex.INTEGER.toInteger(Literals.ten),
				Ex.INTEGER.toInteger(Literals.twenty)
			),
			test3(),
			test4()
		);		
	}
	
	public static final Integer test2(Integer value1, Integer value2) {
		return Ex.INTEGER.multiply(
			value2,
			test5(
				Ex.LONG.toLong(value1)
			)
		);
	}
	
	public static final Double test3() {
		return Ex.DOUBLE.divide(
			Literals.ten,
			Literals.thirty,
			Literals.one
		);
	}

	public static final Integer test4() {
		return Ex.INTEGER.multiply(
			Literals.ten,
			Literals.thirty,
			Literals.one
		);
	}
	
	public static final Long test5(Long value1) {
		return Ex.LONG.subtract(
			value1,
			Literals.one
		);
	}
	
	public static final String test6(String val1, String val2) {
		return Ex.STRING.concatenate(
			val1,
			Literals.ten,
			val2
		);
	}

}
