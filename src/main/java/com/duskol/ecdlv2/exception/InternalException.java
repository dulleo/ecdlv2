package com.duskol.ecdlv2.exception;

import com.duskol.ecdlv2.error.ErrorCodes;

public class InternalException extends Exception {
	
	private static final long serialVersionUID = 6239488571213268187L;
	
	private ErrorCodes errorCode;

	public InternalException() {
	}

	public InternalException(String message) {
		super(message);
	}

	public InternalException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public InternalException(String message, Throwable e) {
		super(message, e);
	}

	public InternalException(String message, Throwable e, ErrorCodes errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}

	public InternalException(String message, Exception e) {
		super(message, e);
	}

	public InternalException(String message, Exception e, ErrorCodes errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}

	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}

}
