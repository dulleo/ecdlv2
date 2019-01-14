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

import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.service.TestService;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@RestController
@RequestMapping(value="/ecdlv2")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/tests")
	public List<TestDTO> getAllTests() {
		return testService.getAll();
	}
	
	@PostMapping("/tests")
	public void createTest(@Valid @RequestBody TestDTO testDTO) {
		testService.save(testDTO);
	}
	
	@PutMapping("/tests/{testId}")
	public void updateTest(@PathVariable Long testId, @Valid @RequestBody TestDTO testDTO) {
		testService.updateTest(testId,testDTO);
	}

	
}
