package com.project.hospitalmanagementbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.service.TestResultService;

@SpringBootTest
public class TestResultControllerTest {

	@InjectMocks
	private TestResultController testResultController;
	
	@Mock
	private TestResultService testResultService;
	
	@Test
	public void testGetTestResults() {
		User user=new User(1L,"john","smith",LocalDate.parse("1998-03-26"),"male","8545635212","abc@gmail.com","pass12$","patient");
    	Patient patient=new Patient("PAT99996",user);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, null,null);
		List<TestResult> testResultList=new ArrayList<TestResult>();
		testResultList.add(testResult);
		when(testResultService.getTestResults(patient.getPatientId())).thenReturn(testResultList);
		assertEquals(testResultController.getTestResults(patient.getPatientId()), new ResponseEntity<>(testResultList, HttpStatus.OK));
	}
	
}
