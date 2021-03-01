package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.exception.FacilityNotFoundException;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.repository.FacilityRepository;
import com.project.hospitalmanagementbackend.repository.HospitalFacilityRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;

@SpringBootTest
public class HospitalFacilityServiceTest {
	
	@InjectMocks
	HospitalFacilityService hospitalFacilityService;
	
	
	@Mock
	HospitalFacilityRepository hospitalFacilityRepository;
	
	@Mock 
	HospitalService hospitalService;
	
	@Mock
	FacilityService facilityService;
	
	@Test
	void testAddHospitalFacility() {
		
		HospitalFacility hospitalFacility = new HospitalFacility();
		Hospital hospital = new Hospital();
		Facility facility = new Facility();
		when(hospitalService.getHospitalById("hId")).thenReturn(hospital);
		when(facilityService.getFacilityById(1)).thenReturn(facility);
		when(hospitalFacilityRepository.save(hospitalFacility)).thenReturn(hospitalFacility);
		
		assertEquals("Facility added to hospital",hospitalFacilityService.addHospitalFacility(hospitalFacility,"hId",1));
	}
	
	@Test
	void testUpdateHospitalFacility() {
		
		Hospital hospital = new Hospital();
		Facility facility = new Facility();
		HospitalFacility hospitalFacility = new HospitalFacility(1L,null,null,"desc","remarks",null);
		when(hospitalService.getHospitalById("hId")).thenReturn(hospital);
		when(facilityService.getFacilityById(1L)).thenReturn(facility);
		when(hospitalFacilityRepository.findById(1L)).thenReturn(Optional.of(hospitalFacility));
		when(hospitalFacilityRepository.save(hospitalFacility)).thenReturn(hospitalFacility);
		assertEquals("Facility Updated Successfully!",hospitalFacilityService.updateHospitalFacility(hospitalFacility,"hId",1L));
	}
	
	@Test
	void testUpdateHospitalFacilityFailure() {
		
		Hospital hospital = new Hospital();
		Facility facility = new Facility();
		HospitalFacility hospitalFacility = new HospitalFacility(2L,null,null,"desc","remarks",null);
		when(hospitalService.getHospitalById("hId")).thenReturn(hospital);
		when(facilityService.getFacilityById(1L)).thenReturn(facility);
		when(hospitalFacilityRepository.findById(1L)).thenReturn(Optional.of(hospitalFacility));
		when(hospitalFacilityRepository.save(hospitalFacility)).thenReturn(hospitalFacility);
		assertThrows(FacilityNotFoundException.class,()->hospitalFacilityService.updateHospitalFacility(hospitalFacility,"hId",2L));
	}
	

}
