package com.project.hospitalmanagementbackend.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.service.HospitalFacilityService;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HospitalFacilityControllerTest {
	
	@InjectMocks
	HospitalFacilityController hospitalFacilityController;
	
	@Mock
	HospitalFacilityService hospitalFacilityService;
	
	@Test
	public void testAddHospitalFacility() {
		HospitalFacility hospitalFacility = new HospitalFacility();
		when(hospitalFacilityService.addHospitalFacility(hospitalFacility,"hId",1)).thenReturn("Added Successfully");
		assertEquals(new ResponseEntity<>("Added Successfully",HttpStatus.OK),hospitalFacilityController.addHospitalFacility(hospitalFacility,"hId",1));
	}
	
	@Test
	public void testUpdateHospitalFacility() {
		HospitalFacility hospitalFacility = new HospitalFacility();
		when(hospitalFacilityService.updateHospitalFacility(hospitalFacility)).thenReturn("Updated Successfully");
		assertEquals(new ResponseEntity<>("Updated Successfully",HttpStatus.OK),hospitalFacilityController.updateHospitalFacility(hospitalFacility));
	}

}
