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

import com.project.hospitalmanagementbackend.dto.BillingInfo;
import com.project.hospitalmanagementbackend.service.BillingService;

@SpringBootTest
public class BillingControllerTest {
	
	@InjectMocks
	BillingController billingController;
	
	@Mock
	BillingService billingService;
	
	@Test
	void testGenerateBillByPatientId() {
		
		List<BillingInfo> billingInfoList = new ArrayList<>();
		when(billingService.generateBillByPatientId("hId", "pId")).thenReturn(billingInfoList);
		assertEquals(new ResponseEntity<>(billingInfoList, HttpStatus.OK), billingController.getPatientBill("hId", "pId"));
		
	}
	
	@Test
	void testPayBillByPatient() {
		
		String msg="Bills Paid Successfully!!";
		when(billingService.payPatientBill("hId", "pId")).thenReturn(msg);
		assertEquals(new ResponseEntity<>(msg, HttpStatus.OK), billingController.payPatientBill("hId", "pId"));
		
	}

}
