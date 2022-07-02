package com.nagarro.statements.exception;
/**
 * @author 
 * Danish Ansari
 *
 */
public class ExceptionResponse {

	private String errorMessage;
	private int status;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [errorMessage=" + errorMessage + ", status=" + status + "]";
	}
	
}
