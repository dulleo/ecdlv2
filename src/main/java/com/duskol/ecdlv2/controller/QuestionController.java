package com.duskol.ecdlv2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;
import com.duskol.ecdlv2.service.QuestionServiceInterface;

/**
 * 
 * Cretaed Jan 15, 2019 by duskol
 *
 */
@RestController
@RequestMapping(value="")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {
	
	@Autowired
	private QuestionServiceInterface questionService;
	
	@RequestMapping(value="/ecdl/tests/{testId}/questions", method= RequestMethod.POST)
	public void createQuestion(@PathVariable Long testId, @Valid @RequestBody QuestionDTO questionDTO) throws ResourceNotFoundException {
		questionService.createQuestion(testId, questionDTO);
	}
	
	@RequestMapping(value="/ecdl/tests/{testId}/questions/{questionId}", method= RequestMethod.PUT)
	public void updateQuestion(@PathVariable Long testId, @PathVariable Long questionId, @Valid @RequestBody QuestionDTO questionDTO) throws ResourceNotFoundException {
		questionService.updateQuestion(testId, questionId, questionDTO);
	}
	
	@RequestMapping(value="/ecdl/tests/{testId}/questions/{questionId}", method= RequestMethod.DELETE)
	public void deleteQuestion(@PathVariable Long testId, @PathVariable Long questionId) throws ResourceNotFoundException {
		questionService.delete(testId, questionId);
	}
}
