package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.service.TestResultService;

@RestController
@RequestMapping("/testresults")
public class TestResultController {

	@Autowired
	private TestResultService testResultService;
	
	@GetMapping("/{patientId}")
	public ResponseEntity<?> getTestResults(@RequestParam String patientId){
		List<TestResult> testResultList=testResultService.getTestResults(patientId);
		return new ResponseEntity<>(testResultList, HttpStatus.OK);
	}
	
}
