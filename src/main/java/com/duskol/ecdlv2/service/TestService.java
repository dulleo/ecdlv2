package com.duskol.ecdlv2.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdlv2.converter.DtoToEntityConverter;
import com.duskol.ecdlv2.converter.EntityToDtoConverter;
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
	private EntityToDtoConverter entityToDtoConverter; 
	
	@Autowired
	private DtoToEntityConverter dtoToEntityConverter;
	
	@Autowired
	private QuestionServiceInterface questionService;
	
	@Autowired
	private EntityProviderInterface entityProvider;
	
	
	@Override
	public List<TestDTO> getAll() {
		
		List<Test> tests = repositoryContainer.getTestRepository().findAll();
		
		List<TestDTO> testDTOs = new ArrayList<>();
		
		if(!tests.isEmpty()) {
			tests.stream().forEach(test -> {
				TestDTO testDTO = new TestDTO();
				entityToDtoConverter.convert(test, testDTO);
				testDTOs.add(testDTO);
			});
		}
	
		return testDTOs;
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
