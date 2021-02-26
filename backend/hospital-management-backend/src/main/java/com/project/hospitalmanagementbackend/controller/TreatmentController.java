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

import com.project.hospitalmanagementbackend.model.TreatmentHistory;
import com.project.hospitalmanagementbackend.service.TreatmentService;

@RestController
@RequestMapping("/treatmenthistory")
public class TreatmentController {

	@Autowired
	TreatmentService treatmentService;
	@PostMapping("/{patientId}/{doctorId}")
	public ResponseEntity<?> updateTreatmentHistory(@PathVariable("patientId") String patientId,@PathVariable("doctorId") String doctorId,@RequestBody TreatmentHistory history) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(treatmentService.updateTreatmentHistory(patientId, doctorId,history),HttpStatus.CREATED);
	}
	
	@GetMapping("/{patientId}")
	public ResponseEntity<?> getAllTreatmentHistory(@PathVariable("patientId") String patientId) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(treatmentService.getAllTreatmentHistory(patientId),HttpStatus.OK);
	}

	@GetMapping("/{patientId}/{doctorId}")
	public ResponseEntity<?> getTreatmentHistory(@PathVariable("patientId") String patientId,@PathVariable("doctorId") String doctorId) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(treatmentService.getTreatmentHistory(patientId, doctorId),HttpStatus.OK);
	}
	
	

}
