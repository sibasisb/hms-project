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

import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.service.FacilityService;

@SpringBootTest
public class FacilityControllerTest {
	
	@InjectMocks
	FacilityController facilityController;
	
	@Mock
	private FacilityService facilityService;
	
	@Test
	void testGetAllFacilities() {
		
		List<Facility> list = new ArrayList<>();
		when(facilityService.getAllFacilities()).thenReturn(list);
		assertEquals(new ResponseEntity<>(list,HttpStatus.OK),facilityController.getAllFacilities() );
	}
	
	@Test
	void testGetFacilityById() {
		
		Facility facility = new Facility();
		when(facilityService.getFacilityById(1)).thenReturn(facility);
		assertEquals(new ResponseEntity<>(facility,HttpStatus.OK),facilityController.getFacilityById(1));
	}

}
