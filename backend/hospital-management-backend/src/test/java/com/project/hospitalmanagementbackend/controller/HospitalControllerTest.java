package com.project.hospitalmanagementbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.dto.DoctorInfo;
import com.project.hospitalmanagementbackend.dto.HospitalInfo;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.service.HospitalService;

@SpringBootTest
public class HospitalControllerTest {
	
	@InjectMocks
	HospitalController hospitalController;
	
	@Mock
	HospitalService hospitalService;
	
	@Test
	void  testGetAllHospitals() {
		
		List<HospitalInfo> hospitalInfoList = new ArrayList<>();
		when(hospitalService.getAllHospitals()).thenReturn(hospitalInfoList);
		assertEquals(new ResponseEntity<>(hospitalService.getAllHospitals(), HttpStatus.OK), hospitalController.getAllHospitals());
	}
	
	@Test
	void  testGetHospitalById() {
		
		Hospital hospital = new Hospital();
		when(hospitalService.getHospitalById("hId")).thenReturn(hospital);
		assertEquals(new ResponseEntity<>(hospital,HttpStatus.OK), hospitalController.getHospitalById("hId"));
	}
	
	@Test 
	void testGetHospitalFacilities(){
		 Set<HospitalFacility> hospitalFacilities = new HashSet<>();
		 when(hospitalService.getHospitalFacilities("hId")).thenReturn(hospitalFacilities);
		 assertEquals(new ResponseEntity<>(hospitalFacilities,HttpStatus.OK), hospitalController.getHospitalFacilities("hId"));
	}
	
	@Test 
	void testGetHospitalDoctors(){
		 List<DoctorInfo> hospitalDoctors = new ArrayList<>();
		 when(hospitalService.getHospitalDoctors("hId")).thenReturn(hospitalDoctors);
		 assertEquals(new ResponseEntity<>(hospitalDoctors,HttpStatus.OK), hospitalController.getHospitalDocotors("hId"));
	}

}
