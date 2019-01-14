package com.duskol.ecdlv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;
import com.duskol.ecdlv2.service.QuestionService;

/**
 * 
 * Cretaed Jan 15, 2019 by duskol
 *
 */
@RestController
@RequestMapping(value="/ecdl/tests")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/{testId}/questions")
	public List<QuestionDTO> getAllQuestions(@PathVariable Long testId) throws ResourceNotFoundException {
		return questionService.getAllQuestions(testId);
	}

}
