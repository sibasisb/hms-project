package com.project.hospitalmanagementbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;
import com.project.hospitalmanagementbackend.repository.HospitalFacilityRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@Service
public class AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	HospitalFacilityRepository hospitalFacilityRepository;
	
	@Transactional
	public List<Appointment> getAllAppointmentsByUser(String patientId) {
		// Patient patient = patientRepository.findById(patientId).get();
		
		return appointmentRepository.getAllAppointmentsByPatient(patientId);
	}

	@Transactional
	public String bookAnAppointment(String patientId, String hospitalId, String serviceId,
			Appointment appointment) {
		Patient patient = patientRepository.findById(patientId).get();
		appointment.setPatient(patient);
		
		Hospital hospital = hospitalRepository.findById(hospitalId).get();
		appointment.setHospital(hospital);
		
		if(serviceId.toLowerCase().charAt(0) == 'd') {
			Doctor doctor = doctorRepository.findById(serviceId).get();
			appointment.setDoctor(doctor);
			
			appointmentRepository.save(appointment);
		}
		else {
			HospitalFacility hospitalFacility = hospitalFacilityRepository.findById(Long.valueOf(serviceId)).get();
			appointment.setHospitalFacility(hospitalFacility);
			
			appointmentRepository.save(appointment);
		}
		return "created";
	}

}
