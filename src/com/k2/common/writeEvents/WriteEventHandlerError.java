package com.k2.common.writeEvents;

public class WriteEventHandlerError extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1592296886261981971L;

	public WriteEventHandlerError() {
	}

	public WriteEventHandlerError(String message) {
		super(message);
	}

	public WriteEventHandlerError(Throwable cause) {
		super(cause);
	}

	public WriteEventHandlerError(String message, Throwable cause) {
		super(message, cause);
	}

	public WriteEventHandlerError(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
