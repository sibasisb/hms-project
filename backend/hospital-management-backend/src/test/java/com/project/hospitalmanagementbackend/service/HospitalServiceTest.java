package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.project.hospitalmanagementbackend.dto.HospitalInfo;
import com.project.hospitalmanagementbackend.exception.HospitalNotFoundException;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.model.User;
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
	void testGetHospitalByIdFailure() {
		
		Hospital hospital = new Hospital();
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		assertThrows(HospitalNotFoundException.class, ()->hospitalService.getHospitalById("ppId"));
	}
	
	@Test
	void testGetHospitalFacilities() {
		
		Hospital hospital = new Hospital();
		HospitalFacility hospitalFacility= new HospitalFacility();
		Set<HospitalFacility> hospitalFacilityList= new HashSet<HospitalFacility>();
		hospitalFacilityList.add(hospitalFacility);
		hospital.setHospitalFacilities(hospitalFacilityList);
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		assertEquals(hospitalFacilityList.size(), hospitalService.getHospitalFacilities("hId").size());
		
	}
	
	@Test
	void testGetHospitalDoctors() {
		
		User user = new User(null,"f","l",null,"","","","","");
		Hospital hospital = new Hospital();
		Doctor doctor = new Doctor();
		doctor.setUser(user);
		Set<Doctor> doctorList = new HashSet<Doctor>();
		doctorList.add(doctor);
		hospital.setDoctors(doctorList);
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		assertEquals(doctorList.size(), hospitalService.getHospitalDoctors("hId").size());
		
	}
	
	

}
