package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.InPatientInfo;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.InPatient;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.InPatientRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@SpringBootTest
public class InPatientServiceTest {
	
	@InjectMocks
	InPatientService inPatientService;
	
	@Mock
	HospitalService hospitalService;
	
	@Mock
	PatientRepository patientRepository;
	
	@Mock
	InPatientRepository inPatientRepository;
	
	@Test
	void testGetInPatientsByHospital() {
		
		User user = new User(1L,"f","l",null,null,"female","","","");
		Patient p = new Patient("PAT001",user);
		InPatient inPatient = new InPatient(1L,p,null,null,null,new BigDecimal(2000),false);
		List<InPatient> inPatientList = new ArrayList<>();
		inPatientList.add(inPatient);
		InPatientInfo inPatientInfo = new InPatientInfo();
		List<InPatientInfo> inPatientInfoList =new ArrayList<>();
		inPatientInfoList.add(inPatientInfo);
		
		when(inPatientRepository.findInPatientByHospital("hId")).thenReturn(inPatientList);
		assertEquals(inPatientInfoList.size(), inPatientService.getInPatientsByHospital("hId").size());
		
	}
	
	@Test
	void testAddInPatient() {
		InPatient inPatient= new InPatient();
		Hospital hospital = new Hospital();
		Patient patient = new Patient();
		when(hospitalService.getHospitalById("hId")).thenReturn(hospital);
		when(patientRepository.findById("pId")).thenReturn(Optional.of(patient));
		when(inPatientRepository.save(inPatient)).thenReturn(inPatient);
		assertEquals("InPatient added successfully", inPatientService.addInPatient(inPatient, "hId", "pId"));
	}
	
	@Test
	void testUpdateInPatient() {
		
		InPatient inPatient= new InPatient(1L,null,null,null,null,new BigDecimal(2000),false);
		Hospital hospital = new Hospital();
		Patient patient = new Patient();
		when(hospitalService.getHospitalById("hId")).thenReturn(hospital);
		when(patientRepository.findById("pId")).thenReturn(Optional.of(patient));
		when(inPatientRepository.save(inPatient)).thenReturn(inPatient);
		when(inPatientRepository.save(inPatient)).thenReturn(inPatient);
		when(inPatientRepository.findById(1L)).thenReturn(Optional.of(inPatient));
		assertEquals("Patient details updated !!", inPatientService.updateInPatient(inPatient, "hId", "pId"));
	}

}
