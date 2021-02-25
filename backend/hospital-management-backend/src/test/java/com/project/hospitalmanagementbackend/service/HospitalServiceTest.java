package com.project.hospitalmanagementbackend.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;

@SpringBootTest
public class HospitalServiceTest {
	
	@InjectMocks
	HospitalService hospitalService;
	
	@Mock
	HospitalRepository hospitalRepository;
	
	@Test
	void testGetAllHospitals() {
		
		Set<Hospital> hospitalList = new HashSet<>();
		when(hospitalRepository.getAllHospitals()).thenReturn(hospitalList);
		assertEquals(hospitalList, hospitalService.getAllHospitals());
	}
	
	@Test
	void testGetHospitalById() {
		
		Hospital hospital = new Hospital();
		when(hospitalRepository.findById("hId")).thenReturn(Optional.of(hospital));
		assertEquals(hospital, hospitalService.getHospitalById("hId"));
	}
	
	

}
