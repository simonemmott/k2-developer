package com.k2.common.spring;

public class ApplicationError extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2302430895700700450L;

	public ApplicationError() {
		// TODO Auto-generated constructor stub
	}

	public ApplicationError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ApplicationError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ApplicationError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ApplicationError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
