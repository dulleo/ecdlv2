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
	TEST_CAN_NOT_BE_DELETED(5),
	
	QUESTION_NOT_FOUND(6),
	QUESTIONS_NOT_FOUND(7), 
	ANSWERS_NOT_FOUND(8), 
	COMPLETED_EXAM_NOT_FOUND(9), 
	
	QUESTION_CAN_NOT_BE_CREATED(10), 
	QUESTION_CAN_NOT_BE_PROVIDED(11), 
	QUESTIONS_CAN_NOT_BE_PROVIDED(12), 
	QUESTION_CAN_NOT_BE_DELETED(13), 
	QUESTION_CAN_NOT_BE_UPDATED(14);
	
	
	private final Integer code;
	
	ErrorCodes(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
}
