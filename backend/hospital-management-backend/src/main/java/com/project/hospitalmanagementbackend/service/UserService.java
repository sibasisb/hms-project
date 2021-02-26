package com.project.hospitalmanagementbackend.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.AuthRequestUser;
import com.project.hospitalmanagementbackend.dto.AuthResponseUser;
import com.project.hospitalmanagementbackend.dto.UserInfo;
import com.project.hospitalmanagementbackend.exception.InvalidUserException;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.HospitalAdmin;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.SystemAdmin;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;
import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;
import com.project.hospitalmanagementbackend.repository.SystemAdminRepository;
import com.project.hospitalmanagementbackend.repository.UserRepository;

import lombok.extern.java.Log;


@Log
@Service
public class UserService {

	@Autowired
	SystemAdminRepository sysAdminRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	HospitalAdminRepository hospitalAdminRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;

	@Transactional
	public String getAdmin(SystemAdmin sysadmin) {
		// TODO Auto-generated method stub
		Optional<SystemAdmin> admin = sysAdminRepository.findById(sysadmin.getUserId());
		if (admin.isPresent()) {
			return "jwttoken";
		} else {
			throw new InvalidUserException("Invalid UserId/Password");
		}
	}

	@Transactional
	public AuthResponseUser getUser(AuthRequestUser user) {
		// TODO Auto-generated method stub
		int found=0;
		if(user.getUserId().length()>3) 
		{
		String userPrefix = user.getUserId().substring(0, 3);
		switch (userPrefix) {
		case "PAT": {
			Optional<Patient> patient = patientRepository.findById(user.getUserId());
			if (patient.isPresent()) {
				found=1;
				User userFound = patient.get().getUser();
				return new AuthResponseUser(patient.get().getPatientId(),userFound.getFirstName(), userFound.getRole(), "jwttoken");
			}
			break;
		}
		case "DOC": {
			Optional<Doctor> doctor = doctorRepository.findById(user.getUserId());
			if (doctor.isPresent()) {
				found=1;
				User userFound = doctor.get().getUser();
				return new AuthResponseUser(doctor.get().getDoctorId(),userFound.getFirstName(), userFound.getRole(), "jwttoken");
			}
			break;
		}
		case "HAD": {
			Optional<HospitalAdmin> hospitalAdmin = hospitalAdminRepository.findById(user.getUserId());
			if (hospitalAdmin.isPresent()) {
				found=1;
				User userFound = hospitalAdmin.get().getUser();
				return new AuthResponseUser(hospitalAdmin.get().getHospitalAdminId(),userFound.getFirstName(), userFound.getRole(), "jwttoken");
			}
			break;
		}
		default:
			throw new InvalidUserException("Invalid UserId/Password");
		}
		}
		if(found==0)
			throw new InvalidUserException("Invalid UserId/Password");
		return null;

	}

	@Transactional
	public String register(UserInfo userInfo) {
		// TODO Auto-generated method stub
		log.info(userInfo.toString());
		String role=userInfo.getUser().getRole();
		switch(role)
		{
		case "doctor":
		{
			Doctor doctor=userInfo.getDoctor();
			doctor.setUser(userInfo.getUser());
			doctor.getHospital().add(hospitalRepository.findById(userInfo.getHospital().getHospitalId()).get());
			doctorRepository.save(doctor);
			break;
		}
		case "hospital admin":
		{
			log.info("something");
			HospitalAdmin hosAdmin=new HospitalAdmin();
			hosAdmin.setHospital(userInfo.getHospital());
			hosAdmin.setUser(userInfo.getUser());
			hospitalAdminRepository.save(hosAdmin);
			break;
		}
		default :
		{
			Patient patient = new Patient();
			patient.setUser(userInfo.getUser());
			patientRepository.save(patient);
		}
		}
		return "User Registered Successfully";
		
	}

}
