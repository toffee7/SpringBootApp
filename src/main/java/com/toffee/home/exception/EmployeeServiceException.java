package com.toffee.home.exception;

public class EmployeeServiceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int statusCode;
	public EmployeeServiceException(int statusCode, String message, Throwable e) {
		super(message,e);
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public EmployeeServiceException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}
}
