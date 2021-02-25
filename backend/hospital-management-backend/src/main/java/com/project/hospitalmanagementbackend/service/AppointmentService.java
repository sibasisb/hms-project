package com.project.hospitalmanagementbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@Service
public class AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	PatientRepository patientRepository;

	public List<Appointment> getAllAppointmentsByUser(String patientId) {
		Patient patient = patientRepository.findById(patientId).get();
		return appointmentRepository.findAllByPatient(patient);
	}

}
