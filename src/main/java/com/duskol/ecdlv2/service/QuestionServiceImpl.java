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
		
		Test test = getTest(testId);
		List<Question> questions = repositoryContainer.getQuestionRepository().findByTestId(test.getId());
		
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
		Test test = getTest(testId);
		Question question = new Question();
		dtoToEntityConverter.convert(questionDTO, question);
		question.setTest(test);
		setAnswers(questionDTO, question);
		repositoryContainer.getQuestionRepository().save(question);
	}

	@Override
	public void update(Long testId, Long questionId, QuestionDTO questionDTO) throws ResourceNotFoundException {
		Test test = getTest(testId);
		Question question = getQuestion(questionId);
		dtoToEntityConverter.convert(questionDTO, question);
		question.setTest(test);
		setAnswers(questionDTO, question);
		repositoryContainer.getQuestionRepository().save(question);
	}
	
	@Override
	public void delete(Long testId, Long questionId) throws ResourceNotFoundException {
		
		Question question = repositoryContainer.getQuestionRepository().findByIdAndTestId();
		
		if(question == null) {
			throw new ResourceNotFoundException("Question not found with id " + questionId + " and testId " + testId);
		}
		
		repositoryContainer.getQuestionRepository().delete(question);
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
	private Test getTest(Long testId) throws ResourceNotFoundException {
		Optional<Test> testOptional = repositoryContainer.getTestRepository().findById(testId);
		
		if(!testOptional.isPresent()) {
			throw new ResourceNotFoundException(getErrorMessage(testId), ErrorCodes.TEST_NOT_FOUND);
		}
		return testOptional.get();
	}
	
	/**
	 * 
	 * @param questionId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private Question getQuestion(Long questionId) throws ResourceNotFoundException {
		Optional<Question> questionOptional = repositoryContainer.getQuestionRepository().findById(questionId);
		
		if(!questionOptional.isPresent()) {
			throw new ResourceNotFoundException(getErrorMessage(questionId), ErrorCodes.QUESTION_NOT_FOUND);
		}
		return questionOptional.get();
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
