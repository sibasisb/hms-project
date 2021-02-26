package com.project.hospitalmanagementbackend.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.PatientInfo;
import com.project.hospitalmanagementbackend.exception.PatientNotFoundException;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Transactional
	public List<Patient> findPatientsFromDoctorId(String doctorId){
		List<Appointment> appointmentList=appointmentRepository.findAppointmentsByDoctorId(doctorId);
		List<Patient> patientList=new ArrayList<Patient>();
		for(Appointment ap: appointmentList) {
			patientList.add(ap.getPatient());
		}
		return patientList;
	}

	public PatientInfo findPatientByPatientId(String patientId) {
		Optional<Patient> poption=patientRepository.findById(patientId);
		if(!poption.isPresent()) {
			throw new PatientNotFoundException("Patient not found");
		}
		Patient p=poption.get();
		PatientInfo patInfo=new PatientInfo();
		patInfo.setPatientId(p.getPatientId());
		patInfo.setFirstName(p.getUser().getFirstName());
		patInfo.setLastName(p.getUser().getLastName());
		patInfo.setDateOfBirth(p.getUser().getDateOfBirth());
		patInfo.setContact(p.getUser().getContact());
		patInfo.setGender(p.getUser().getGender());
		return patInfo;
	}
	
}
