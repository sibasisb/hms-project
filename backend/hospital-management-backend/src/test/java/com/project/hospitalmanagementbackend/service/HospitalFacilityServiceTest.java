package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.HospitalFacilityInfo;
import com.project.hospitalmanagementbackend.exception.FacilityNotFoundException;
import com.project.hospitalmanagementbackend.exception.HospitalFacilityNotFoundException;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;
import com.project.hospitalmanagementbackend.repository.HospitalFacilityRepository;

@SpringBootTest
public class HospitalFacilityServiceTest {
	
	@InjectMocks
	HospitalFacilityService hospitalFacilityService;
	
	
	@Mock
	HospitalFacilityRepository hospitalFacilityRepository;
	
	@Mock
	HospitalAdminRepository hospitalAdminRepository;
	
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
	void testGetFacilitiesHospitalId() {
		String hospitalId="HOS000001";
		String hospitalAdminId="HAD0998";
		HospitalFacility hospitalFacility = new HospitalFacility(1L,null,null,"desc","remarks",null);
		List<HospitalFacility> hospitalFacilityList=new ArrayList<>();
		hospitalFacilityList.add(hospitalFacility);
		when(hospitalAdminRepository.getHospitalIdByAdminId(hospitalAdminId)).thenReturn(hospitalId);
		when(hospitalFacilityRepository.getFacilitiesByHospitalId(hospitalId)).thenReturn(hospitalFacilityList);
		assertEquals(hospitalFacilityList,hospitalFacilityService.getFacilitiesHospitalId(hospitalAdminId));
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
	
	@Test
	void testGetHospitalFacilityById() {
		
		Facility facility = new Facility(1L,"name",new ArrayList<>());
		HospitalFacility hospitalFacility = new HospitalFacility();
		hospitalFacility.setFacility(facility);
		HospitalFacilityInfo hospitalFacilityInfo = new HospitalFacilityInfo();
		hospitalFacilityInfo.setFacilityName("name");
		when(hospitalFacilityRepository.findById(1L)).thenReturn(Optional.of(hospitalFacility));
		assertEquals(hospitalFacilityInfo.getFacilityName(),hospitalFacilityService.getHospitalFacilityById(1l).getFacilityName());
	}
	
	@Test
	void testGetHospitalFacilityByIdFailure() {
		
		Facility facility = new Facility(1L,"name",new ArrayList<>());
		HospitalFacility hospitalFacility = new HospitalFacility();
		hospitalFacility.setFacility(facility);
		HospitalFacilityInfo hospitalFacilityInfo = new HospitalFacilityInfo();
		hospitalFacilityInfo.setFacilityName("name");
		when(hospitalFacilityRepository.findById(1L)).thenReturn(Optional.of(hospitalFacility));
		assertThrows(HospitalFacilityNotFoundException.class,()->hospitalFacilityService.getHospitalFacilityById(2L).getFacilityName());
	}
	

}
