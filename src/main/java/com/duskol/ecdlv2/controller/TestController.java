package com.duskol.ecdlv2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;
import com.duskol.ecdlv2.service.TestServiceInterface;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@RestController
@RequestMapping(value="")
//@CrossOrigin(origins = "http://localhost:4200")
public class TestController {
	
	@Autowired
	private TestServiceInterface testService;
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/ecdl/tests", method= RequestMethod.GET)
	public List<TestDTO> getAllTests() {
		return testService.getAll();
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/ecdl/tests/{id}", method= RequestMethod.GET)
	public TestDTO getTest(@PathVariable Long id) throws ResourceNotFoundException {
		return testService.getTest(id);
	}
	
	@RequestMapping(value="/ecdl/tests", method= RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createTest(@Valid @RequestBody TestDTO testDTO) {
		testService.save(testDTO);
	}
	
	@PutMapping("/tests/{testId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateTest(@PathVariable Long testId, @Valid @RequestBody TestDTO testDTO) throws ResourceNotFoundException {
		testService.update(testId,testDTO);
	}
	
	@DeleteMapping("/tests/{testId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteTest(@PathVariable Long testId) throws ResourceNotFoundException {
		testService.delete(testId);
	}

	
}
