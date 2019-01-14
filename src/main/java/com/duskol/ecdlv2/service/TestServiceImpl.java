package com.duskol.ecdlv2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duskol.ecdlv2.converter.DtoToEntityConverter;
import com.duskol.ecdlv2.converter.EntityToDtoConverter;
import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Test;
import com.duskol.ecdlv2.repository.TestRepository;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private EntityToDtoConverter entityToDtoConverter; 
	
	@Autowired
	private DtoToEntityConverter dtoToEntityConverter; 
	
	
	@Override
	public List<TestDTO> getAll() {
		
		List<Test> tests = testRepository.findAll();
		
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
		testRepository.save(test);
	}
}
