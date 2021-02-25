package com.project.hospitalmanagementbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public List<Appointment> getPendingAppointents(){
		return appointmentRepository.findPatientsWithFacilityRequests();
	}
	
}
