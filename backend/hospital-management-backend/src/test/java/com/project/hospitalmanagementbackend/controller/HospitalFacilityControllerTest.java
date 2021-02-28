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

import java.util.ArrayList;
import java.util.List;

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
		when(hospitalFacilityService.updateHospitalFacility(hospitalFacility,"hId",1L)).thenReturn("Updated Successfully");
		assertEquals(new ResponseEntity<>("Updated Successfully",HttpStatus.OK),hospitalFacilityController.updateHospitalFacility(hospitalFacility,"hId",1L));
	}
	
	@Test
	public void testGetTestFacilities() {
		String hospitalAdminId="HAD0998";
		HospitalFacility hospitalFacility = new HospitalFacility(1L,null,null,"desc","remarks",null);
		List<HospitalFacility> hospitalFacilityList=new ArrayList<>();
		hospitalFacilityList.add(hospitalFacility);
		when(hospitalFacilityService.getFacilitiesHospitalId(hospitalAdminId)).thenReturn(hospitalFacilityList);
		assertEquals(new ResponseEntity<>(hospitalFacilityList,HttpStatus.OK),hospitalFacilityController.getTestFacilities(hospitalAdminId));
	}

}
