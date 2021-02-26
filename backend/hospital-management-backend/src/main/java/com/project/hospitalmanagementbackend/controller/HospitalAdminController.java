package com.project.hospitalmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.service.HospitalAdminService;

@RestController
@RequestMapping("/hospitaladmin")
public class HospitalAdminController {

	@Autowired
	private HospitalAdminService hospitalAdminService;
	
	@GetMapping("/{hospitalAdminId}")
	public ResponseEntity<?> getHospitalIdByAdminId(@PathVariable("hospitalAdminId") String hospitalAdminId){
		String hospitalId=hospitalAdminService.getHospitalIdByAdminId(hospitalAdminId);
		return new ResponseEntity<>(hospitalId,HttpStatus.OK);
	}
	
}
