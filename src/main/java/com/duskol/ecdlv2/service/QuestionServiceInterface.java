package com.duskol.ecdlv2.service;

import java.util.List;

import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;

/**
 * 
 * Cretaed Jan 15, 2019 by duskol
 *
 */
public interface QuestionServiceInterface {

	List<QuestionDTO> getAllQuestions(Long testId) throws ResourceNotFoundException;

	void create(Long testId, QuestionDTO questionDTO) throws ResourceNotFoundException;

	void update(Long testId, Long questionId, QuestionDTO questionDTO) throws ResourceNotFoundException;

	void delete(Long testId, Long questionId) throws ResourceNotFoundException;

	QuestionDTO getQuestion(Long testId, Long questionId) throws ResourceNotFoundException;

	void deleteAllQuestionsForTest(Long testId);

}