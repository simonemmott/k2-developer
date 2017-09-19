package com.k2.common.util.impl;

import java.text.SimpleDateFormat;

import com.k2.common.util.K2Utilities;

public class K2UtilitiesImpl implements K2Utilities {

	private SimpleDateFormat dateFormatter;
	@Override
	public SimpleDateFormat getDateFormatter() {
		if (dateFormatter == null) { dateFormatter = new SimpleDateFormat("dd-MM-yyyy"); }
		return dateFormatter;
	}

	private SimpleDateFormat datetimeFormatter;
	@Override
	public SimpleDateFormat getDateTimeFormatter() {
		if (datetimeFormatter == null) { datetimeFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS"); }
		return datetimeFormatter;
	}

}
