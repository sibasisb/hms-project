package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.AppointmentInfo;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;
import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;
import com.project.hospitalmanagementbackend.repository.HospitalFacilityRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@Autowired
	private HospitalAdminRepository hospitalAdminRepository;

	@Transactional
	public List<AppointmentInfo> getAllAppointmentsByUser(String patientId) {
		// Set<Appointment> appointments = appointmentRepository.findByPatient_PatientId(patientId).stream().collect(Collectors.toSet());
		
		Set<Appointment> appointments = appointmentRepository.getAllAppointmentsByPatient(patientId);
		System.out.println(appointments);
		List<AppointmentInfo> appointmentInfoList = new ArrayList<AppointmentInfo>();
		appointments.forEach((appointment->{
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentDate(appointment.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointment.getAppointmentTime());
			if(appointment.getHospitalFacility()==null)
			appointmentInfo.setDoctorName(appointment.getDoctor().getUser().getFirstName()+" "+appointment.getDoctor().getUser().getLastName());
			else
			appointmentInfo.setFacilityName(appointment.getHospitalFacility().getFacility().getName());
			
			appointmentInfo.setHospitalName(appointment.getHospital().getName());
			appointmentInfoList.add(appointmentInfo);
		}));
	
		
		// Please DONT delete this line
//		for (Appointment appointment : appointments) {
//			appointment.setPatient(appointment.getPatient());
//			appointment.setHospital(appointment.getHospital());
//			if(appointment.getDoctor() == null) 
//				appointment.setHospitalFacility(appointment.getHospitalFacility());
//			else
//				appointment.setDoctor(appointment.getDoctor());
//		}
//		appointments.forEach((appointment) -> log.info(appointment.toString()));
		return appointmentInfoList;
	}

	@Transactional
	public String bookAnAppointment(String patientId, String hospitalId, String serviceId, Appointment appointment) {
		log.info(patientId);
		log.info(hospitalId);
		log.info(serviceId);
		Patient patient = patientRepository.findById(patientId).get();
		appointment.setPatient(patient);

		Hospital hospital = hospitalRepository.findById(hospitalId).get();
		appointment.setHospital(hospital);

		if (serviceId.toLowerCase().charAt(0) == 'd') {
			Doctor doctor = doctorRepository.findById(serviceId).get();
			appointment.setDoctor(doctor);
			log.info(doctor.getSpeciality());
			appointmentRepository.save(appointment);
		} else {
			HospitalFacility hospitalFacility = hospitalFacilityRepository.findById(Long.valueOf(serviceId)).get();
			appointment.setHospitalFacility(hospitalFacility);
			log.info(hospitalFacility.getFacility().getName());
			appointmentRepository.save(appointment);
		}
		return "created";
	}
	
	public List<Appointment> getPendingAppointents(String hospitalAdminId){
		String hospitalId=hospitalAdminRepository.getHospitalIdByAdminId(hospitalAdminId);
		return appointmentRepository.findPatientsWithFacilityRequests(hospitalId);
	}

}
