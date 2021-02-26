package com.project.hospitalmanagementbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.service.HospitalAdminService;

@SpringBootTest
public class HospitalAdminControllerTest {

	@InjectMocks
	private HospitalAdminController hospitalAdminController;
	
	@Mock
	private HospitalAdminService hospitalAdminService;
	
	@Test
	public void testGetHospitalIdByAdminId() {
		String hospitalAdminId="HAD00001";
		String hospitalId="HOS00001";
		when(hospitalAdminService.getHospitalIdByAdminId(hospitalAdminId)).thenReturn(hospitalId);
		assertEquals(new ResponseEntity<>(hospitalId,HttpStatus.OK), hospitalAdminController.getHospitalIdByAdminId(hospitalAdminId));
	}
	
}
