package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.HospitalInfo;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;



@Service
public class HospitalService {
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Transactional
	public List<HospitalInfo> getAllHospitals(){
		
		Set<Hospital> hospitals = hospitalRepository.getAllHospitals();
		List<HospitalInfo> hospitalInfoList = new ArrayList<HospitalInfo>();
		hospitals.forEach((hospital->{
			HospitalInfo hospitalInfo =new HospitalInfo();
			hospitalInfo.setHospitalId(hospital.getHospitalId());
			hospitalInfo.setName(hospital.getName());
			hospitalInfo.setPhone(hospital.getPhone());
			hospitalInfo.setWebsite(hospital.getWebsite());
			hospitalInfo.setAddress(hospital.getAddress());
			hospitalInfoList.add(hospitalInfo);
		}));
		return hospitalInfoList;
	}
	
	@Transactional
	public Hospital getHospitalById(String hospitalId) {
		
		Optional<Hospital> hospital = hospitalRepository.getHospitalById(hospitalId);
		if(hospital.isPresent())
		{
			return hospital.get();
		}
		else
			throw new RuntimeException("Hospital Not Found");
	}

	public Set<HospitalFacility> getHospitalFacilities(String hospitalId) {
		// 
		Optional<Hospital> hospital = hospitalRepository.getHospitalById(hospitalId);
		
		return hospital.get().getHospitalFacilities();
	}
	
	public Set<Doctor> getHospitalDoctors(String hospitalId) {
		// 
		Optional<Hospital> hospital = hospitalRepository.getHospitalById(hospitalId);
		
		return hospital.get().getDoctors();
	}

}
