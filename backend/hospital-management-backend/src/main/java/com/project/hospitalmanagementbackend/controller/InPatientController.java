package com.project.hospitalmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.InPatient;
import com.project.hospitalmanagementbackend.service.InPatientService;

@RestController
@RequestMapping("/inpatients")
public class InPatientController {

	@Autowired
	InPatientService inPatientService;

	@GetMapping("/{hospitalId}")
	public ResponseEntity<?> getInPatientsByHospital(@PathVariable("hospitalId") String hospitalId) {

		return new ResponseEntity<>(inPatientService.getInPatientsByHospital(hospitalId), HttpStatus.OK);
	}

	@PostMapping("/add/{hospitalId}/{patientId}")
	public ResponseEntity<?> addInPatient(@RequestBody InPatient inPatient,
			@PathVariable("hospitalId") String hospitalId, @PathVariable("patientId") String patientId) {

		return new ResponseEntity<>(inPatientService.addInPatient(inPatient, hospitalId, patientId), HttpStatus.OK);

	}

	@PostMapping("/update/{hospitalId}/{patientId}")
	public ResponseEntity<?> updateInPatient(@RequestBody InPatient inPatient,
			@PathVariable("hospitalId") String hospitalId, @PathVariable("patientId") String patientId) {

		return new ResponseEntity<>(inPatientService.updateInPatient(inPatient,hospitalId,patientId), HttpStatus.OK);
	}
}
