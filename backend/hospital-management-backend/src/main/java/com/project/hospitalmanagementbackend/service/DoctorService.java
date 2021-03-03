package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.DoctorInfo;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;

@Service
@Transactional
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public List<DoctorInfo> getAllDoctors(){
		
		List<Doctor> doctorList = doctorRepository.findAll();
		List<DoctorInfo> doctorInfoList = new ArrayList<>();
		doctorList.forEach((doctor->{
			DoctorInfo doctorInfo = new DoctorInfo();
			doctorInfo.setDoctorId(doctor.getDoctorId());
			doctorInfo.setName(doctor.getUser().getFirstName()+" "+doctor.getUser().getLastName());
			doctorInfo.setExperience(doctor.getExperience());
			doctorInfo.setQualification(doctor.getQualification());
			doctorInfo.setCharge(doctor.getCharge());
			doctorInfo.setSpeciality(doctor.getSpeciality());
			doctor.getHospital().forEach((hospital->{
				doctorInfo.getHospitalNames().add(hospital.getName());
			}));
			doctorInfoList.add(doctorInfo);
		}));
		return doctorInfoList;
	}

}
