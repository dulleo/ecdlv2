package com.duskol.ecdlv2.service;

import java.util.List;

import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.exception.EntityValidationException;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
public interface TestServiceInterface {

	List<TestDTO> getAll();

	void createTest(TestDTO testDTO);

	void updateTest(Long testId, TestDTO testDTO) throws ResourceNotFoundException, EntityValidationException;

	void deleteTest(Long testId) throws ResourceNotFoundException;

	TestDTO getTest(Long id) throws ResourceNotFoundException;

}
