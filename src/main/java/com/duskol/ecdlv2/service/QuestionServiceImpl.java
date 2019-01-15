package com.duskol.ecdlv2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdlv2.converter.DtoToEntityConverter;
import com.duskol.ecdlv2.converter.EntityToDtoConverter;
import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.entity.Answer;
import com.duskol.ecdlv2.entity.Question;
import com.duskol.ecdlv2.entity.Test;
import com.duskol.ecdlv2.error.ErrorCodes;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;
import com.duskol.ecdlv2.repository.RepositoryContainer;

/**
 * 
 * Cretaed Jan 15, 2019 by duskol
 *
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private RepositoryContainer repositoryContainer;
	
	@Autowired
	private EntityToDtoConverter entityToDtoConverter; 
	
	@Autowired
	private DtoToEntityConverter dtoToEntityConverter; 

	@Override
	public List<QuestionDTO> getAllQuestions(Long testId) throws ResourceNotFoundException {
		
		Optional<Test> testOptional = getOptionalTest(testId);
		List<Question> questions = repositoryContainer.getQuestionRepository().findByTestId(testOptional.get().getId());
		
		List<QuestionDTO> questionDTOs = new ArrayList<>();
		
		if(!questions.isEmpty()) {
			questions.stream().forEach(question -> {
				QuestionDTO questionDTO = new QuestionDTO();
				entityToDtoConverter.convert(question, questionDTO);
			});
		}
		
		return questionDTOs;
	}
	
	@Override
	public void create(Long testId, QuestionDTO questionDTO) throws ResourceNotFoundException {
		Optional<Test> testOptional = getOptionalTest(testId);
		
		Question question = new Question();
		dtoToEntityConverter.convert(questionDTO, question);
		question.setTest(testOptional.get());
		setAnswers(questionDTO, question);
		
		repositoryContainer.getQuestionRepository().save(question);
	}

	/**
	 * Set question answers
	 * @param questionDTO
	 * @param question
	 * @param answers
	 */
	private void setAnswers(QuestionDTO questionDTO, Question question) {
		
		List<Answer> answers = new ArrayList<Answer>();
		
		questionDTO.getAnswers().stream().forEach(answerDTO -> {
			Answer answer = new Answer();
			dtoToEntityConverter.convert(answerDTO, answer);
			answers.add(answer);
		});
		question.setAnswers(answers);
	}
	
	/**
	 * 
	 * @param testId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private Optional<Test> getOptionalTest(Long testId) throws ResourceNotFoundException {
		Optional<Test> testOptional = repositoryContainer.getTestRepository().findById(testId);
		
		if(!testOptional.isPresent()) {
			throw new ResourceNotFoundException(getErrorMessage(testId), ErrorCodes.TEST_NOT_FOUND);
		}
		return testOptional;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private String getErrorMessage(Long id) {
		return "Test id: " + id + " not found!";
	}
}
