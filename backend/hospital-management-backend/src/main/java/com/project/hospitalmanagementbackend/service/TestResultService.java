package com.project.hospitalmanagementbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.repository.TestResultRepository;

@Service
public class TestResultService {

	@Autowired
	private TestResultRepository testResultRepository;
	
	public List<TestResult> getTestResults(String patientId,Long appointmentId){
		return testResultRepository.getTestResults(patientId,appointmentId);
	}
	
}
