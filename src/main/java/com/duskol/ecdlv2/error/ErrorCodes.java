package com.duskol.ecdlv2.error;

/**
 * 
 * Cretaed Jan 14, 2019 by duskol
 *
 */
public enum ErrorCodes {

	TEST_NOT_FOUND(1),
	QUESTION_NOT_FOUND(2),
	QUESTIONS_NOT_FOUND(3), 
	ANSWERS_NOT_FOUND(4), 
	COMPLETED_EXAM_NOT_FOUND(5), 
	TESTS_NOT_FOUND(6), 
	TEST_CAN_NOT_BE_CREATED(7), 
	TEST_CAN_NOT_BE_PROVIDED(8), 
	TESTS_CAN_NOT_BE_PROVIDED(9),
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
