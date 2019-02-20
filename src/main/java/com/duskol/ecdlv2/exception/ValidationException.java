package com.duskol.ecdlv2.exception;

import com.duskol.ecdlv2.error.ErrorCodes;

/**
 * 
 * Cretaed Feb 20, 2019 by duskol
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = -7382912066979099295L;
	
	private ErrorCodes errorCode;

	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}
	
	public ValidationException() {}
	
	public ValidationException(String meesge) {
		super(meesge);
	}
	
	public ValidationException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ValidationException(String message, Throwable e) {
		super(message, e);
	}

	public ValidationException(String message, Throwable e, ErrorCodes errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}

	public ValidationException(String message, Exception e) {
		super(message, e);
	}

	public ValidationException(String message, Exception e, ErrorCodes errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}
}
