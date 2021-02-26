package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;

@SpringBootTest
public class HospitalAdminServiceTest {

	@InjectMocks
	private HospitalAdminService hospitalAdminService;
	
	@Mock
	private HospitalAdminRepository hospitalAdminRepository;
	
	@Test
	public void testGetHospitalIdByAdminId() {
		String hospitalAdminId="HAD00001";
		String hospitalId="HOS00001";
		when(hospitalAdminRepository.getHospitalIdByAdminId(hospitalAdminId)).thenReturn(hospitalId);
		assertEquals(hospitalId, hospitalAdminService.getHospitalIdByAdminId(hospitalAdminId));
	}
	
}
