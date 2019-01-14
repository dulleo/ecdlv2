package com.duskol.ecdlv2.service;

import java.util.List;

import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;

/**
 * 
 * Cretaed Jan 15, 2019 by duskol
 *
 */
public interface QuestionService {

	List<QuestionDTO> getAllQuestions(Long testId) throws ResourceNotFoundException;

	void createQuestion(Long testId, QuestionDTO questionDTO) throws ResourceNotFoundException;

}
