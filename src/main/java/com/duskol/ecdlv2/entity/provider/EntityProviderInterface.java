package com.duskol.ecdlv2.entity.provider;

import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Question;
import com.duskol.ecdlv2.entity.Test;
import com.duskol.ecdlv2.exception.EntityValidationException;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;

/**
 * 
 * Cretaed Jan 19, 2019 by duskol
 *
 */
public interface EntityProviderInterface {
	
	Test getTest(Long testId) throws ResourceNotFoundException;
	
	Question getQuestion(Long testId, Long questionId) throws ResourceNotFoundException;

	Test getTest(Long testId, TestDTO testDTO) throws EntityValidationException, ResourceNotFoundException;

}
