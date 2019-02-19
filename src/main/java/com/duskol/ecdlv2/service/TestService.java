package com.duskol.ecdlv2.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdlv2.converter.DtoToEntityConverterInterface;
import com.duskol.ecdlv2.converter.EntityToDtoConverterInterface;
import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Test;
import com.duskol.ecdlv2.entity.provider.EntityProviderInterface;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;
import com.duskol.ecdlv2.repository.RepositoryContainer;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@Service
@Transactional
public class TestService implements TestServiceInterface {

	@Autowired
	private RepositoryContainer repositoryContainer;
	
	@Autowired
	private EntityToDtoConverterInterface entityToDtoConverter; 
	
	@Autowired
	private DtoToEntityConverterInterface dtoToEntityConverter;
	
	@Autowired
	private QuestionServiceInterface questionService;
	
	@Autowired
	private EntityProviderInterface entityProvider;
	
	
	@Override
	public List<TestDTO> getAll() {
		
		List<TestDTO> testDTOs = new ArrayList<>();
		
		List<Test> tests = repositoryContainer.getTestRepository().findAll();
		
		if(!tests.isEmpty()) {
			tests.stream().forEach(test -> {
				TestDTO testDTO = new TestDTO();
				entityToDtoConverter.convert(test, testDTO);
				//List<QuestionDTO> questionDTOs = new ArrayList<>();
				//questionDTOs = questionService.getTestQuestions(test.getId());
				//testDTO.setQuestions(questionDTOs);
				//testDTO.setTotalQuestions(testDTO.getQuestions().size());
				Integer totalQuestions = questionService.getTotalQuestions(test.getId());
				testDTO.setTotalQuestions(totalQuestions);
				testDTOs.add(testDTO);
			});
		}
	
		return testDTOs;
	}
	
	@Override
	public TestDTO getTest(Long testId) throws ResourceNotFoundException {
		Test test = entityProvider.getTest(testId);
		TestDTO testDTO = new TestDTO();
		entityToDtoConverter.convert(test, testDTO);
		List<QuestionDTO> questionDTOs = new ArrayList<>();
		questionDTOs = questionService.getTestQuestionDTOs(test.getId());
		testDTO.setQuestions(questionDTOs);
		testDTO.setTotalQuestions(questionDTOs.size());
		
		return testDTO;
	}

	/**
	 * This method creates a test
	 */
	@Override
	public void save(TestDTO testDTO) {
		Test test = new Test();
		dtoToEntityConverter.convert(testDTO, test);
		repositoryContainer.getTestRepository().save(test);
	}

	/**
	 * This method updates test
	 * @throws ResourceNotFoundException 
	 */
	@Override
	public void update(Long testId, TestDTO testDTO) throws ResourceNotFoundException {
		
		Test test = entityProvider.getTest(testId);
		dtoToEntityConverter.convert(testDTO, test);
		repositoryContainer.getTestRepository().save(test);
	}

	/**
	 * This method deletes a test
	 * @throws ResourceNotFoundException 
	 */
	@Override
	public void delete(Long testId) throws ResourceNotFoundException {
		
		Test test = entityProvider.getTest(testId);
		
		//before deleting test, you need to delete all questions, because question has reference to the answer
		questionService.deleteAllQuestionsForTest(testId);
		repositoryContainer.getTestRepository().delete(test);
	}

	
}
