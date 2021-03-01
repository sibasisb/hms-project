package com.project.hospitalmanagementbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.exception.FacilityNotFoundException;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;
import com.project.hospitalmanagementbackend.repository.HospitalFacilityRepository;

@Service
public class HospitalFacilityService {

	@Autowired
	HospitalFacilityRepository hospitalFacilityRepository;

	@Autowired
	FacilityService facilityService;

	@Autowired
	HospitalService hospitalService;

	@Autowired
	private HospitalAdminRepository hospitalAdminRepository;

	@Transactional
	public List<HospitalFacility> getFacilitiesHospitalId(String hospitalAdminId) {
		String hospitalId = hospitalAdminRepository.getHospitalIdByAdminId(hospitalAdminId);
		return hospitalFacilityRepository.getFacilitiesByHospitalId(hospitalId);
	}

	public String addHospitalFacility(HospitalFacility hospitalFacility, String hospitalId, long facilityId) {

		Hospital hospital = hospitalService.getHospitalById(hospitalId);
		Facility facility = facilityService.getFacilityById(facilityId);
		hospitalFacility.setHospital(hospital);
		hospitalFacility.setFacility(facility);
		hospitalFacilityRepository.save(hospitalFacility);
		return "Facility added to hospital";
	}

	public String updateHospitalFacility(HospitalFacility hospitalFacility,String hospitalId,long facilityId) {

		
		Optional<HospitalFacility> findFacility = hospitalFacilityRepository
				.findById(hospitalFacility.getHospitalFacilityId());
		if (findFacility.isPresent()) {
			Hospital hospital = hospitalService.getHospitalById(hospitalId);
			Facility facility = facilityService.getFacilityById(facilityId);
			hospitalFacility.setHospital(hospital);
			hospitalFacility.setFacility(facility);
			hospitalFacilityRepository.save(hospitalFacility);
			return "Facility Updated Successfully!";
		} else
			throw new FacilityNotFoundException("Facility Not Found!");

	}
	
	

}
