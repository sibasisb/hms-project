package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.TestResultRepository;

@SpringBootTest
public class TestResultServiceTest {

	@InjectMocks
	private TestResultService testResultService;
	
	@Mock
	private TestResultRepository testResultRepository;
	
	@Test
	public void testGetTestResults() {
		User user=new User(1L,"john","smith",LocalDate.parse("1998-03-26"),"male","8545635212","abc@gmail.com","pass12$","patient");
    	Patient patient=new Patient("PAT99996",user);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, null,null);
		List<TestResult> testResultList=new ArrayList<TestResult>();
		testResultList.add(testResult);
		when(testResultRepository.getTestResults(patient.getPatientId())).thenReturn(testResultList);
		assertEquals(testResultService.getTestResults(patient.getPatientId()),testResultList);
	}
	
}
