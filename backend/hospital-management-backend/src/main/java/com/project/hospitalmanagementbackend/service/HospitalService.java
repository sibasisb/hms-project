package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.DoctorInfo;
import com.project.hospitalmanagementbackend.dto.HospitalInfo;
import com.project.hospitalmanagementbackend.exception.HospitalNotFoundException;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;



@Service
@Transactional
public class HospitalService {
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	
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
	
	
	public Hospital getHospitalById(String hospitalId) {
		
		Optional<Hospital> hospital = hospitalRepository.getHospitalById(hospitalId);
		if(hospital.isPresent())
		{
			return hospital.get();
		}
		else
			throw new HospitalNotFoundException("Hospital Not Found");
	}

	public Set<HospitalFacility> getHospitalFacilities(String hospitalId) {
		// 
		Optional<Hospital> hospital = hospitalRepository.getHospitalById(hospitalId);
		
		return hospital.get().getHospitalFacilities();
	}
	
	public List<DoctorInfo> getHospitalDoctors(String hospitalId) {
		// 
		Optional<Hospital> hospital = hospitalRepository.getHospitalById(hospitalId);
		
		Set<Doctor> doctorList = hospital.get().getDoctors();
		List<DoctorInfo> doctoInfoList = new ArrayList<DoctorInfo>();
		doctorList.forEach((doctor->{
			DoctorInfo doctorInfo = new DoctorInfo();
			doctorInfo.setDoctorId(doctor.getDoctorId());
			doctorInfo.setName(doctor.getUser().getFirstName()+" "+doctor.getUser().getLastName());
			doctorInfo.setAvailableDays(doctor.getAvailableDays());
			doctorInfo.setAvailableTime(doctor.getAvailableTime());
			doctorInfo.setExperience(doctor.getExperience());
			doctorInfo.setQualification(doctor.getQualification());
			doctorInfo.setSpeciality(doctor.getSpeciality());
			doctorInfo.setCharge(doctor.getCharge());
			
			doctoInfoList.add(doctorInfo);
			
		}));
		return doctoInfoList;
	}

}
