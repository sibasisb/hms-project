package com.project.hospitalmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.service.DoctorService;

@RestController
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/doctors")
	public ResponseEntity<?> getAllDoctors(){
		
		return new ResponseEntity<>(doctorService.getAllDoctors(),HttpStatus.OK);
	}

}
