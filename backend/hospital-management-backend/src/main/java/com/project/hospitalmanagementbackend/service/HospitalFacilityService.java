package com.project.hospitalmanagementbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;
import com.project.hospitalmanagementbackend.repository.HospitalFacilityRepository;

@Service
public class HospitalFacilityService {

	@Autowired
	private HospitalFacilityRepository hospitalFacilityRepository;
	@Autowired
	private HospitalAdminRepository hospitalAdminRepository;
	
	@Transactional
	public List<HospitalFacility> getFacilitiesHospitalId(Long hospitalAdminId){
		String hospitalId=hospitalAdminRepository.getHospitalIdByAdminId(hospitalAdminId);
		return hospitalFacilityRepository.getFacilitiesByHospitalId(hospitalId);
	}
	
}
