package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.service.HospitalFacilityService;

@RestController
@RequestMapping("/hospitalfacilities")
public class HospitalFacilityController {

	
	@Autowired
	private HospitalFacilityService hospitalFacilityService;
	
	@GetMapping("/{hospitalAdminId}")
	public ResponseEntity<?> getTestFacilities(@PathVariable("hospitalAdminId") Long hospitalAdminId){
		List<HospitalFacility> hospitalFacilities=hospitalFacilityService.getFacilitiesHospitalId(hospitalAdminId);
		return new ResponseEntity<>(hospitalFacilities,HttpStatus.OK);
	}
	
}
