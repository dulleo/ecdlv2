package com.duskol.ecdlv2.service;

import java.util.List;

import com.duskol.ecdlv2.dto.TestDTO;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
public interface TestService {

	List<TestDTO> getAll();

	void save(TestDTO testDTO);

	void updateTest(Long testId, TestDTO testDTO);

}
