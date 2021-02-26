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
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.service.PatientService;

@SpringBootTest
public class PatientControllerTest {

	@InjectMocks
	private PatientController patientController;
	
	@Mock
	private PatientService patientService;
	
	@Test
	public void testGetPatientsByDoctorId() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		List<Patient> patientList=new ArrayList<Patient>();
		patientList.add(patient);
		String doctorId="DOC000001";
		when(patientService.findPatientsFromDoctorId(doctorId)).thenReturn(patientList);
		assertEquals(patientController.getPatientsByDoctorId(doctorId),new ResponseEntity<>(patientList, HttpStatus.OK));
	}
	
}
