package com.project.hospitalmanagementbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.repository.FacilityRepository;

@Service
public class FacilityService {
	
	@Autowired
	FacilityRepository facilityRepository;
	
	@Transactional
	public List<Facility> getAllFacilities(){
		
		return facilityRepository.findAll();
	}
	
	public Facility getFacilityById(long facilityId) {
		Optional<Facility> facility = facilityRepository.findById(facilityId);
		if(facility.isPresent())
			return facility.get();
		else
			throw new RuntimeException("Facility Not Found!");
	}

}
