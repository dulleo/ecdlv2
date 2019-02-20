package com.duskol.ecdlv2.entity.provider;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Question;
import com.duskol.ecdlv2.entity.Test;
import com.duskol.ecdlv2.exception.EntityValidationException;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;
import com.duskol.ecdlv2.repository.RepositoryContainer;

/**
 * 
 * Cretaed Jan 19, 2019 by duskol
 *
 */
@Component
public class EntityProvider implements EntityProviderInterface {

	@Autowired
	private RepositoryContainer repositoryContainer;

	/**
	 * 
	 * @param testId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@Override
	public Test getTest(Long testId) throws ResourceNotFoundException {

		Optional<Test> testOptional = repositoryContainer.getTestRepository().findById(testId);

		if (!testOptional.isPresent()) {
			throw new ResourceNotFoundException("Test id: " + testId + " not found!");
		}
		return testOptional.get();
	}
	
	@Override
	public Test getTest(Long testId, TestDTO testDTO) throws EntityValidationException, ResourceNotFoundException {
		
		if(testId.longValue() != testDTO.getId().longValue()) {
			throw new EntityValidationException("Test id from url: " + testId + " and from dto object: " + testDTO.getId() + ", are not the same!");
		}
		
		return this.getTest(testId);
	}

	@Override
	public Question getQuestion(Long testId, Long questionId) throws ResourceNotFoundException {

		Question question = repositoryContainer.getQuestionRepository().findByIdAndTestId(questionId, testId);

		if (question == null) {
			throw new ResourceNotFoundException("Question not found with id " + questionId + " and testId " + testId);
		}
		return question;
	}

	

}
