package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.service.FacilityService;

@RestController
@RequestMapping("/facilities")
public class FacilityController {

	@Autowired
	FacilityService facilityService;

	@GetMapping("/")
	public ResponseEntity<?> getAllFacilities() {
		List<Facility> facilityList = facilityService.getAllFacilities();
		return new ResponseEntity<>(facilityList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getFacilityById(@PathVariable("id") long facilityId) {
		return new ResponseEntity<>(facilityService.getFacilityById(facilityId), HttpStatus.OK);
	}

}
