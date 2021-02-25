package com.project.hospitalmanagementbackend.service;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;

@Service
public class HospitalService {
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Transactional
	public Set<Hospital> getAllHospitals(){
		
		return hospitalRepository.getAllHospitals();
	}
	
	@Transactional
	public Hospital getHospitalById(String hospitalId) {
		
		Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);
		if(hospital.isPresent())
			return hospital.get();
		else
			throw new RuntimeException("Hospital Not Found");
	}

}
