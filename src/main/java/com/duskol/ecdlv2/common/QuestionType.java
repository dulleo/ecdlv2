package com.duskol.ecdlv2.common;

public enum QuestionType {
	
	SINGLE("SINGLE"),
	MULTIPLE("MULTIPLE");
	
	private final String type;
	
	QuestionType(final String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
