package com.duskol.ecdlv2.error;

/**
 * 
 * Cretaed Jan 14, 2019 by duskol
 *
 */
public class ErrorResponse {

	private Integer code;
	private String message;
	
	public ErrorResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}
