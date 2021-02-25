package com.project.hospitalmanagementbackend.service;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HospitalService {
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Transactional
	public Set<Hospital> getAllHospitals(){
		//Set<Hospital> hospitals = hospitalRepository.findAll().stream().collect(Collectors.toSet()) ;
		Set<Hospital> hospitals = hospitalRepository.getAllHospitals();
//		//hospitals.forEach(System.out::println);
//		hospitals.forEach((hospital)->{
//			hospital.getHospitalFacilities();
//			hospital.getDoctors();
//		});
		return hospitals;
	}
	
	@Transactional
	public Hospital getHospitalById(String hospitalId) {
		
		Optional<Hospital> hospital = hospitalRepository.getHospitalById(hospitalId);
		if(hospital.isPresent())
			return hospital.get();
		else
			throw new RuntimeException("Hospital Not Found");
	}

}
