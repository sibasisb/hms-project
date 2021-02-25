package com.project.hospitalmanagementbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private HospitalAdminRepository hospitalAdminRepository;
	
	public List<Appointment> getPendingAppointents(Long hospitalAdminId){
		String hospitalId=hospitalAdminRepository.getHospitalIdByAdminId(hospitalAdminId);
		return appointmentRepository.findPatientsWithFacilityRequests(hospitalId);
	}
	
}
