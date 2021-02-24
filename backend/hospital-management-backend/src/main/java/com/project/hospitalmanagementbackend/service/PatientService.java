package com.project.hospitalmanagementbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public List<Patient> getPatientsWithFacilityRequests(){
		return patientRepository.findAll();
	}
	
}
