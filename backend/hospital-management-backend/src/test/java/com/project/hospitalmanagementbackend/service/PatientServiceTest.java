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
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@SpringBootTest
public class PatientServiceTest {

	@InjectMocks
	private PatientService patientService;
	
    @Mock
    private PatientRepository patientRepository;
    
    @Test
    public void testGetPatientsWithFacilityRequests() {
    	User user=new User(1L,"john","smith",LocalDate.parse("1998-03-26"),"male","8545635212","abc@gmail.com","pass12$","patient");
    	Patient patient=new Patient("PAT99996",user);
    	List<Patient> patientList=new ArrayList<Patient>();
    	patientList.add(patient);
    	when(patientRepository.findPatientsWithFacilityRequests()).thenReturn(patientList);
    	assertEquals(patientService.getPatientsWithFacilityRequests(),patientList);
    }
    
}
