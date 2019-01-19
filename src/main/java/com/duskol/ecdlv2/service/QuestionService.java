package com.duskol.ecdlv2.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdlv2.converter.DtoToEntityConverter;
import com.duskol.ecdlv2.converter.EntityToDtoConverter;
import com.duskol.ecdlv2.dto.AnswerDTO;
import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.entity.Answer;
import com.duskol.ecdlv2.entity.Question;
import com.duskol.ecdlv2.entity.Test;
import com.duskol.ecdlv2.entity.provider.EntityProviderInterface;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;
import com.duskol.ecdlv2.repository.RepositoryContainer;

/**
 * 
 * Cretaed Jan 15, 2019 by duskol
 *
 */
@Service
@Transactional
public class QuestionService implements QuestionServiceInterface {
	
	@Autowired
	private RepositoryContainer repositoryContainer;
	
	@Autowired
	private EntityToDtoConverter entityToDtoConverter; 
	
	@Autowired
	private DtoToEntityConverter dtoToEntityConverter;
	
	@Autowired
	private EntityProviderInterface entityProvider;

	@Override
	public List<QuestionDTO> getAllQuestions(Long testId) throws ResourceNotFoundException {
		
		Test test = entityProvider.getTest(testId);
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
	public QuestionDTO getQuestion(Long testId, Long questionId) throws ResourceNotFoundException {
		
		Question question = entityProvider.getQuestion(testId, questionId);
		
		QuestionDTO questionDTO = new QuestionDTO();
		entityToDtoConverter.convert(question, questionDTO);
		setAnswerDTOs(question, questionDTO);
		
		return questionDTO;
	}

	@Override
	public void create(Long testId, QuestionDTO questionDTO) throws ResourceNotFoundException {
		
		Test test = entityProvider.getTest(testId);
		Question question = new Question();
		dtoToEntityConverter.convert(questionDTO, question);
		question.setTest(test);
		setAnswers(questionDTO, question);
		repositoryContainer.getQuestionRepository().save(question);
	}

	@Override
	public void update(Long testId, Long questionId, QuestionDTO questionDTO) throws ResourceNotFoundException {
		
		Question question = entityProvider.getQuestion(testId, questionId);
		
		dtoToEntityConverter.convert(questionDTO, question);
		setAnswers(questionDTO, question);
		repositoryContainer.getQuestionRepository().save(question);
	}
	
	@Override
	public void deleteAllQuestionsForTest(Long testId) {
		
		List<Question> questions = repositoryContainer.getQuestionRepository().findByTestId(testId);
		repositoryContainer.getQuestionRepository().deleteAll(questions);
	}
	
	@Override
	public void delete(Long testId, Long questionId) throws ResourceNotFoundException {
		
		Question question = entityProvider.getQuestion(testId, questionId);
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
	 * @param question
	 * @param questionDTO
	 */
	private void setAnswerDTOs(Question question, QuestionDTO questionDTO) {
		
		List<AnswerDTO> answerDTOs = new ArrayList<>();
		
		question.getAnswers().stream().forEach(answer-> {
			AnswerDTO answerDTO = new AnswerDTO();
			entityToDtoConverter.convert(answer, answerDTO);
			answerDTOs.add(answerDTO);
		});
		
		questionDTO.setAnswers(answerDTOs);
	}
}
