package com.duskol.ecdlv2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdlv2.converter.DtoToEntityConverter;
import com.duskol.ecdlv2.converter.EntityToDtoConverter;
import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Test;
import com.duskol.ecdlv2.error.ErrorCodes;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;
import com.duskol.ecdlv2.repository.RepositoryContainer;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private RepositoryContainer repositoryContainer;
	
	@Autowired
	private EntityToDtoConverter entityToDtoConverter; 
	
	@Autowired
	private DtoToEntityConverter dtoToEntityConverter; 
	
	
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
		
		Optional<Test> testOptional = getOptionalTest(testId);
		
		Test test = testOptional.get();
		dtoToEntityConverter.convert(testDTO, test);
		repositoryContainer.getTestRepository().save(test);
	}

	/**
	 * This method deletes a test
	 * @throws ResourceNotFoundException 
	 */
	@Override
	public void delete(Long testId) throws ResourceNotFoundException {
		Optional<Test> testOptional = getOptionalTest(testId);
		repositoryContainer.getTestRepository().delete(testOptional.get());
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
