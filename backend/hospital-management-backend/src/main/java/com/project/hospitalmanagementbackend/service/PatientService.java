package com.project.hospitalmanagementbackend.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;

@Service
public class PatientService {
	
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
	
}
