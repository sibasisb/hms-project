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

import com.project.hospitalmanagementbackend.dto.HospitalInfo;
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
		Hospital hospital = new Hospital("HOS0995","name","address","phone","website",null,null);
		Set<Hospital> hospitalList = new HashSet<>();
		hospitalList.add(hospital);
		HospitalInfo hospitalInfo =new HospitalInfo();
		hospitalInfo.setHospitalId(hospital.getHospitalId());
		hospitalInfo.setName(hospital.getName());
		hospitalInfo.setAddress(hospital.getAddress());
		hospitalInfo.setPhone(hospital.getPhone());
		hospitalInfo.setWebsite(hospital.getWebsite());
		List<HospitalInfo> hospitalInfoList = new ArrayList<>();
		hospitalInfoList.add(hospitalInfo);
		
		when(hospitalRepository.getAllHospitals()).thenReturn(hospitalList);
		assertEquals(hospitalInfoList.size(), hospitalService.getAllHospitals().size());
	}
	
	@Test
	void testGetHospitalById() {
		
		Hospital hospital = new Hospital();
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		assertEquals(hospital, hospitalService.getHospitalById("hId"));
	}
	
	@Test
	void testGetHospitalFacilities() {
		
		Hospital hospital = new Hospital();
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		assertEquals(hospital, hospitalService.getHospitalById("hId"));
		
	}
	
	@Test
	void testGetHospitalDoctors() {
		
		Hospital hospital = new Hospital();
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		assertEquals(hospital, hospitalService.getHospitalById("hId"));
		
	}
	
	

}
