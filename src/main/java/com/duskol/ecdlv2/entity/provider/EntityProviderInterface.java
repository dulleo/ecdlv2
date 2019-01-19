package com.duskol.ecdlv2.entity.provider;

import com.duskol.ecdlv2.entity.Question;
import com.duskol.ecdlv2.entity.Test;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;

/**
 * 
 * Cretaed Jan 19, 2019 by duskol
 *
 */
public interface EntityProviderInterface {
	
	Test getTest(Long testId) throws ResourceNotFoundException;
	
	Question getQuestion(Long testId, Long questionId) throws ResourceNotFoundException;

}
