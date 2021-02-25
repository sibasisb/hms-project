package com.project.hospitalmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.service.HospitalService;

@RestController
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;
	
	@GetMapping("/hospitals")
	public ResponseEntity<?> getAllHospitals() {
		return new ResponseEntity<>(hospitalService.getAllHospitals(),HttpStatus.OK);
	}
	
	@GetMapping("/hospitals/{hospitalId}")
	public ResponseEntity<?> getHospitalById(@PathVariable(name="hospitalId") String hospitalId){
		return new ResponseEntity<>(hospitalService.getHospitalById(hospitalId),HttpStatus.OK);
	}

}
