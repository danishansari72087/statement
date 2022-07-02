package com.nagarro.statements.exception;

public class InvalidRequestException extends Exception {

    private static final long serialVersionUID = 1L;
    private Integer statusCode;
    
    public InvalidRequestException() {
		super();
	}

	public InvalidRequestException(final String message,final Integer statusCode) {
		super(message);
		this.statusCode=statusCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}
	
}