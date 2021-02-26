package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<?> getPatientsByDoctorId(@PathVariable("doctorId") String doctorId){
		List<Patient> patientList=patientService.findPatientsFromDoctorId(doctorId);
		return new ResponseEntity<>(patientList, HttpStatus.OK);
	}
	
}
