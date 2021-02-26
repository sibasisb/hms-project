package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.dto.TestResultPatientInfo;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.service.TestResultService;

@RestController
@RequestMapping("/testresults")
public class TestResultController {

	@Autowired
	private TestResultService testResultService;
	
	//hospital admin can fetch test results by appointment 
	@GetMapping("/fetch")
	public ResponseEntity<?> getTestResults(@RequestBody Appointment appointment){
		List<TestResult> testResultList=testResultService.getTestResults(appointment.getPatient().getPatientId(),appointment.getAppointmentId());
		return new ResponseEntity<>(testResultList, HttpStatus.OK);
	}
	
	//hospital admin can update test results
	@PostMapping("/update")
	public ResponseEntity<?> updateTestResults(@RequestBody TestResult testResult){
		String response=testResultService.updateTestResult(testResult);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//doctor can fetch test results and details of patient from here, so can the patient
	@GetMapping("/{patientId}")
	public ResponseEntity<?> fetchTestResultsAndDetails(@PathVariable("patientId") String patientId){
		List<TestResultPatientInfo> testResultsDetailsList=testResultService.fetchTestResultsAndDetails(patientId);
		return new ResponseEntity<>(testResultsDetailsList, HttpStatus.OK);
	}
	
}
