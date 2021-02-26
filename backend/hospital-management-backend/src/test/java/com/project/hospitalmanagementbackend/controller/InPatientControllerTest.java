package com.project.hospitalmanagementbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.dto.InPatientInfo;
import com.project.hospitalmanagementbackend.model.InPatient;
import com.project.hospitalmanagementbackend.service.InPatientService;

@SpringBootTest
public class InPatientControllerTest {

	
	@InjectMocks
	InPatientController inPatientController;
	
	@Mock
	InPatientService inPatientService;
	
	@Test
	void testGetInPatientsByHospital() {
		List<InPatientInfo> inPatientInfoList = new ArrayList<InPatientInfo>();
		when(inPatientService.getInPatientsByHospital("hId")).thenReturn(inPatientInfoList);
		assertEquals(new ResponseEntity<>(inPatientInfoList,HttpStatus.OK),inPatientController.getInPatientsByHospital("hId"));
	}
	
	@Test
	void testAddInPatient(){
		InPatient inPatient =new InPatient();
		when(inPatientService.addInPatient(inPatient, "hId", "pId")).thenReturn("InPatient Added");
		assertEquals(new ResponseEntity<>("InPatient Added",HttpStatus.OK), inPatientController.addInPatient(inPatient,"hId","pId"));
	}
	@Test
	void testUpdateInPatient(){
		InPatient inPatient =new InPatient();
		when(inPatientService.updateInPatient(inPatient, "hId", "pId")).thenReturn("InPatient Updated");
		assertEquals(new ResponseEntity<>("InPatient Updated",HttpStatus.OK), inPatientController.updateInPatient(inPatient,"hId","pId"));
	}
	
}
