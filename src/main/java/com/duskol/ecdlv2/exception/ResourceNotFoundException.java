package com.duskol.ecdlv2.exception;

/**
 * 
 * Cretaed Jan 14, 2019 by duskol
 *
 */
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = -8642172555338364982L;
	
	public ResourceNotFoundException() {}
	
	public ResourceNotFoundException(String meesge) {
		super(meesge);
	}
}
