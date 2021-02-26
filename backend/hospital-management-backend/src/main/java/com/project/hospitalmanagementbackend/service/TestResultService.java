package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.PatientInfo;
import com.project.hospitalmanagementbackend.dto.TestResultPatientInfo;
import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.repository.TestResultRepository;

@Service
public class TestResultService {

	@Autowired
	private TestResultRepository testResultRepository;
	
	@Autowired
	private PatientService patientService;
	
	public List<TestResult> getTestResults(String patientId,Long appointmentId){
		return testResultRepository.getTestResults(patientId,appointmentId);
	}
	
	@Transactional
	public String updateTestResult(TestResult testResult) {
		TestResult testResultObj=testResultRepository.save(testResult);
		if(testResultObj!=null)
			return "Updated record successfully";
		return "Invalid update";
	}

	public List<TestResultPatientInfo> fetchTestResultsAndDetails(String patientId) {
		List<TestResult> testResults=testResultRepository.getTestResultsByPatientId(patientId);
		PatientInfo patientInfo=patientService.findPatientByPatientId(patientId);
		List<TestResultPatientInfo> testResultPatientInfos=new ArrayList<TestResultPatientInfo>();
		for(TestResult testResult:testResults) {
			TestResultPatientInfo testResultPatientInfo=new TestResultPatientInfo();
			testResultPatientInfo.setResultId(testResult.getResultId());
			testResultPatientInfo.setTestName(testResult.getTestName());
			testResultPatientInfo.setInfos(testResult.getInfos());
			testResultPatientInfo.setContact(patientInfo.getContact());
			testResultPatientInfo.setDateOfBirth(patientInfo.getDateOfBirth());
			testResultPatientInfo.setFirstName(patientInfo.getFirstName());
			testResultPatientInfo.setGender(patientInfo.getGender());
			testResultPatientInfo.setLastName(patientInfo.getLastName());
			testResultPatientInfo.setPatientId(patientInfo.getPatientId());
			testResultPatientInfos.add(testResultPatientInfo);
		}
		return testResultPatientInfos;
	}
	
}
