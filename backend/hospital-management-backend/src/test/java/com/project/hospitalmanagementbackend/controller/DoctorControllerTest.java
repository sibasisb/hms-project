package com.project.hospitalmanagementbackend.controller;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.project.hospitalmanagementbackend.dto.DoctorInfo;
import com.project.hospitalmanagementbackend.service.DoctorService;
import java.util.List;


@SpringBootTest
public class DoctorControllerTest {
	
	@InjectMocks
	DoctorController doctorController;
	
	@Mock
	DoctorService doctorService;
	
	@Test
	void testGetAllDoctors() {
		
		List<DoctorInfo> doctorInfoList = new ArrayList<>();
		when(doctorService.getAllDoctors()).thenReturn(doctorInfoList);
		assertEquals(new ResponseEntity<>(doctorInfoList, HttpStatus.OK), doctorController.getAllDoctors());
		
	}

}
