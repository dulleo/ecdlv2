package com.duskol.ecdlv2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * Cretaed Jan 15, 2019 by duskol
 *
 */
@Component
public class RepositoryContainer {
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	QuestionRepository questionRepository;

	public TestRepository getTestRepository() {
		return testRepository;
	}

	public QuestionRepository getQuestionRepository() {
		return questionRepository;
	}
}
