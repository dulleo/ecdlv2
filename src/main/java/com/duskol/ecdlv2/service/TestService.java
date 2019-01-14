package com.duskol.ecdlv2.service;

import java.util.List;

import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
public interface TestService {

	List<TestDTO> getAll();

	void save(TestDTO testDTO);

	void update(Long testId, TestDTO testDTO) throws ResourceNotFoundException;

	void delete(Long testId);

}
