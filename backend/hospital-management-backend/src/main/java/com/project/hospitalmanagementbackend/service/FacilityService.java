package com.project.hospitalmanagementbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.exception.FacilityNotFoundException;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.repository.FacilityRepository;

@Service
@Transactional
public class FacilityService {

	@Autowired
	FacilityRepository facilityRepository;

	
	public List<Facility> getAllFacilities() {
		List<Facility> facilities = facilityRepository.findAll();
		return facilities;
	}
	
	public Facility getFacilityById(long facilityId) {
		Optional<Facility> facility = facilityRepository.getFacilityById(facilityId);
		if (facility.isPresent())
			return facility.get();
		else
			throw new FacilityNotFoundException("Facility Not Found!");
	}

}
