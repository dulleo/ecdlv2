package com.duskol.ecdlv2.error;

/**
 * 
 * Cretaed Jan 14, 2019 by duskol
 *
 */
public enum ErrorCodes {
	
	TEST_CAN_NOT_BE_PROVIDED(1),
	TEST_CAN_NOT_BE_CREATED(2), 
	TESTS_CAN_NOT_BE_PROVIDED(3),
	TEST_CAN_NOT_BE_UPDATED(4),
	
	QUESTION_NOT_FOUND(5),
	QUESTIONS_NOT_FOUND(6), 
	ANSWERS_NOT_FOUND(7), 
	COMPLETED_EXAM_NOT_FOUND(8), 
	
	QUESTION_CAN_NOT_BE_CREATED(9), 
	QUESTION_CAN_NOT_BE_PROVIDED(10), 
	QUESTIONS_CAN_NOT_BE_PROVIDED(11), 
	QUESTION_CAN_NOT_BE_DELETED(12), 
	QUESTION_CAN_NOT_BE_UPDATED(13); 
	
	
	private final Integer code;
	
	ErrorCodes(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
}
