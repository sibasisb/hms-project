package com.project.hospitalmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.service.HospitalFacilityService;

@RestController
public class HospitalFacilityController {

	@Autowired
	HospitalFacilityService hospitalFacilityService;

	@PostMapping("/addfacility/{hospitalId}/{facilityId}")
	public ResponseEntity<?> addHospitalFacility(@RequestBody HospitalFacility hospitalFacility,
			@PathVariable("hospitalId") String hospitalId, @PathVariable("facilityId") long facilityId) {
		return new ResponseEntity<>(
				hospitalFacilityService.addHospitalFacility(hospitalFacility, hospitalId, facilityId), HttpStatus.OK);
	}

	@PutMapping("/updatefacility")
	public ResponseEntity<?> updateHospitalFacility(@RequestBody HospitalFacility hospitalFacility) {
		return new ResponseEntity<>(hospitalFacilityService.updateHospitalFacility(hospitalFacility), HttpStatus.OK);
	}

	@GetMapping("/{hospitalAdminId}")
	public ResponseEntity<?> getTestFacilities(@PathVariable("hospitalAdminId") String hospitalAdminId) {
		List<HospitalFacility> hospitalFacilities = hospitalFacilityService.getFacilitiesHospitalId(hospitalAdminId);
		return new ResponseEntity<>(hospitalFacilities, HttpStatus.OK);
	}

}
