package com.project.hospitalmanagementbackend.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.dto.TestResultDto;
import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.service.TestResultService;

@RestController
@RequestMapping("/testresults")
public class TestResultController {

	@Autowired
	private TestResultService testResultService;

	// hospital admin can fetch test results by appointment
	@GetMapping("/fetch/{appointmentId}/{patientId}")
	public ResponseEntity<?> getTestResults(@PathVariable("appointmentId") Long appointmentId,
			@PathVariable("patientId") String patientId) {
		Set<TestResultDto> testResultList = testResultService.getTestResults(patientId, appointmentId);
		return new ResponseEntity<>(testResultList, HttpStatus.OK);
	}

	// hospital admin can fetch test results by test result id
	@GetMapping("/getbyid/{testResultId}")
	public ResponseEntity<?> getTestResultById(@PathVariable("testResultId") long testResultId) {
		TestResultDto testResultDto = testResultService.getTestResultByIdService(testResultId);
		return new ResponseEntity<>(testResultDto, HttpStatus.OK);
	}

	// hospital admin can add test result
	@PostMapping("/add/{appointmentId}/{patientId}")
	public ResponseEntity<?> addTestResults(@RequestBody TestResult testResult,
			@PathVariable("appointmentId") Long appointmentId, @PathVariable("patientId") String patientId) {
		String response = testResultService.addTestResult(testResult, patientId, appointmentId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// hospital admin can update test results
	@PutMapping("/update/{appointmentId}/{patientId}/{testResultId}")
	public ResponseEntity<?> updateTestResults(@RequestBody TestResult testResult,
			@PathVariable("appointmentId") Long appointmentId, @PathVariable("patientId") String patientId,
			@PathVariable("testResultId") Long testResultId) {
		String response = testResultService.updateTestResult(testResult, patientId, appointmentId, testResultId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// doctor can fetch test results and details of patient from here, so can the
	// patient
	@GetMapping("/{patientId}")
	public ResponseEntity<?> fetchTestResultsAndDetails(@PathVariable("patientId") String patientId) {
		Set<TestResultDto> testResultsDetailsList = testResultService.fetchTestResultsAndDetails(patientId);
		return new ResponseEntity<>(testResultsDetailsList, HttpStatus.OK);
	}

}
