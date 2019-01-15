package com.duskol.ecdlv2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/{testId}/questions")
	public void createQuestion(@PathVariable Long testId, @Valid @RequestBody QuestionDTO questionDTO) throws ResourceNotFoundException {
		questionService.create(testId, questionDTO);
	}
	
	@PutMapping("/{testId}/questions/{questionId}")
	public void updateQuestion(@PathVariable Long testId, @PathVariable Long questionId, @Valid @RequestBody QuestionDTO questionDTO) {
		questionService.update(testId, questionId, questionDTO);
	}

}
