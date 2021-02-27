package com.project.hospitalmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	@Autowired
	BillingService billingService;
	
	@GetMapping("/{hospitalId}/{patientId}")
	public ResponseEntity<?> getPatientBill(@PathVariable("hospitalId") String hospitalId ,@PathVariable("patientId") String patientId){
		
		return new ResponseEntity<>(billingService.generateBillByPatientId(hospitalId, patientId),HttpStatus.OK);
	}
	
	@PutMapping("/pay/{hospitalId}/{patientId}")
	public ResponseEntity<?> payPatientBill(@PathVariable("hospitalId") String hospitalId ,@PathVariable("patientId") String patientId){
		
		return new ResponseEntity<>(billingService.payPatientBill(hospitalId,patientId),HttpStatus.OK);
	}

}
