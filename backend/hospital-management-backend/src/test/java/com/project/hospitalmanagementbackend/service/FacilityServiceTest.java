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

import com.project.hospitalmanagementbackend.exception.FacilityNotFoundException;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.repository.FacilityRepository;

@SpringBootTest
public class FacilityServiceTest {
	
	@InjectMocks
	FacilityService facilityService;

	@Mock
	FacilityRepository facilityRepository;
	
	@Test
	void testGetAllFacilities() {
		
		List<Facility> list = new ArrayList<>();
		when(facilityRepository.findAll()).thenReturn(list);
		assertEquals(list,facilityService.getAllFacilities() );
	}
	
	@Test
	void testGetFacilityById() {
		
		Facility facility = new Facility();
		when(facilityRepository.getFacilityById(1L)).thenReturn(Optional.of(facility));
		assertEquals(facility,facilityService.getFacilityById(1L));
	}
	
	@Test
	void testGetFacilityByIdFailure() {
		
		Facility facility = new Facility();
		when(facilityRepository.getFacilityById(1L)).thenReturn(Optional.of(facility));
		assertThrows(FacilityNotFoundException.class,()->facilityService.getFacilityById(2L));
	}
	
	
}
