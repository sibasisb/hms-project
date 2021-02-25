package com.project.hospitalmanagementbackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.repository.HospitalFacilityRepository;

@Service
public class HospitalFacilityService {

	@Autowired
	HospitalFacilityRepository hospitalFacilityRepository;
	
	@Autowired
	FacilityService facilityService;
	
	@Autowired
	HospitalService hospitalService;

	public String addHospitalFacility(HospitalFacility hospitalFacility,String hospitalId,long facilityId) {
		
		Hospital hospital = hospitalService.getHospitalById(hospitalId);
		Facility facility =facilityService.getFacilityById(facilityId);
		hospitalFacility.setHospital(hospital);
		hospitalFacility.setFacility(facility);
		hospitalFacilityRepository.save(hospitalFacility);
		return "Facility added to hospital";
	}
	
	public String updateHospitalFacility(HospitalFacility hospitalFacility) {
		
		Optional<HospitalFacility> findFacility = hospitalFacilityRepository
				.findById(hospitalFacility.getHospitalFacilityId());
		if(findFacility.isPresent())
		{
			hospitalFacilityRepository.save(hospitalFacility);
			return "Facility Updated Successfully!";
		}
		else
			throw new RuntimeException("Facility Not Found!");
		
	}

}
