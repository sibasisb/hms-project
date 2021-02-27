package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.AppointmentInfo;
import com.project.hospitalmanagementbackend.exception.AppointmentNotFoundException;
import com.project.hospitalmanagementbackend.exception.DoctorNotFoundException;
import com.project.hospitalmanagementbackend.exception.HospitalFacilityNotFoundException;
import com.project.hospitalmanagementbackend.exception.HospitalNotFoundException;
import com.project.hospitalmanagementbackend.exception.PatientNotFoundException;
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
	public List<AppointmentInfo> getAllAppointmentsByUser(String patientId) throws PatientNotFoundException {
		patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Invalid Patient"));
		Set<Appointment> appointments = appointmentRepository.getAllAppointmentsByPatient(patientId);
		log.info(appointments.toString());
		List<AppointmentInfo> appointmentInfoList = new ArrayList<AppointmentInfo>();
		appointments.forEach((appointment -> {
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentId(appointment.getAppointmentId());
			appointmentInfo.setPatientName(appointment.getPatient().getUser().getFirstName() + " "
					+ appointment.getPatient().getUser().getLastName());
			appointmentInfo.setAppointmentDate(appointment.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointment.getAppointmentTime());
			if (appointment.getHospitalFacility() == null) {
				appointmentInfo.setDoctorName(appointment.getDoctor().getUser().getFirstName() + " "
						+ appointment.getDoctor().getUser().getLastName());
			} else {
				appointmentInfo.setFacilityName(appointment.getHospitalFacility().getFacility().getName());
			}

			appointmentInfo.setHospitalName(appointment.getHospital().getName());
			appointmentInfo.setRemarks(appointment.getRemarks());
			appointmentInfo.setMedicalRecords(appointment.getMedicalRecords());
			appointmentInfo.setApproved(appointment.getApproved());
			appointmentInfoList.add(appointmentInfo);
		}));

		return appointmentInfoList;
	}

	@Transactional
	public String bookAnAppointment(String patientId, String hospitalId, String serviceId, Appointment appointment)
			throws PatientNotFoundException, HospitalNotFoundException, DoctorNotFoundException,
			HospitalFacilityNotFoundException {
		log.info(patientId);
		log.info(hospitalId);
		log.info(serviceId);
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new PatientNotFoundException("Invalid Patient"));
		appointment.setPatient(patient);

		Hospital hospital = hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new HospitalNotFoundException("Invalid Hospital"));
		appointment.setHospital(hospital);

		if (serviceId.toLowerCase().charAt(0) == 'd') {
			Doctor doctor = doctorRepository.findById(serviceId)
					.orElseThrow(() -> new DoctorNotFoundException("Invalid Doctor"));
			appointment.setDoctor(doctor);
			appointment.setPaid(false);
			appointment.setApproved(false);
			log.info(doctor.getSpeciality());
			appointmentRepository.save(appointment);
		} else {
			HospitalFacility hospitalFacility = hospitalFacilityRepository.findById(Long.valueOf(serviceId))
					.orElseThrow(() -> new HospitalFacilityNotFoundException("Invalid Facility"));
			appointment.setHospitalFacility(hospitalFacility);
			appointment.setPaid(false);
			appointment.setApproved(false);
			log.info(hospitalFacility.getFacility().getName());
			appointmentRepository.save(appointment);
		}
		return "created";
	}

	@Transactional
	public List<AppointmentInfo> getPendingAppointents(String hospitalAdminId) {
		String hospitalId = hospitalAdminRepository.getHospitalIdByAdminId(hospitalAdminId);
		List<AppointmentInfo> appointmentInfoList = new ArrayList<AppointmentInfo>();
		List<Appointment> appointments = appointmentRepository.findPatientsWithFacilityRequests(hospitalId);
		appointments.forEach((appointment -> {
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentId(appointment.getAppointmentId());
			appointmentInfo.setPatientName(appointment.getPatient().getUser().getFirstName() + " "
					+ appointment.getPatient().getUser().getLastName());
			appointmentInfo.setAppointmentDate(appointment.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointment.getAppointmentTime());
			if (appointment.getHospitalFacility() == null) {
				appointmentInfo.setDoctorName(appointment.getDoctor().getUser().getFirstName() + " "
						+ appointment.getDoctor().getUser().getLastName());
			} else {
				appointmentInfo.setFacilityName(appointment.getHospitalFacility().getFacility().getName());
			}

			appointmentInfo.setHospitalName(appointment.getHospital().getName());
			appointmentInfo.setRemarks(appointment.getRemarks());
			appointmentInfo.setMedicalRecords(appointment.getMedicalRecords());
			appointmentInfo.setApproved(appointment.getApproved());
			appointmentInfoList.add(appointmentInfo);
		}));
		return appointmentInfoList;
	}

	@Transactional
	public List<AppointmentInfo> getAllAppointmentsByDoctor(String doctorId) {
		doctorRepository.findById(doctorId).orElseThrow(() -> new DoctorNotFoundException("Invalid Doctor"));
		List<Appointment> appointments = appointmentRepository.findByDoctor_DoctorId(doctorId);
		List<AppointmentInfo> appointmentInfoList = new ArrayList<AppointmentInfo>();
		appointments.forEach((appointment -> {
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentId(appointment.getAppointmentId());
			appointmentInfo.setPatientName(appointment.getPatient().getUser().getFirstName() + " "
					+ appointment.getPatient().getUser().getLastName());
			appointmentInfo.setAppointmentDate(appointment.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointment.getAppointmentTime());
			appointmentInfo.setDoctorName(appointment.getDoctor().getUser().getFirstName() + " "
					+ appointment.getDoctor().getUser().getLastName());

			appointmentInfo.setHospitalName(appointment.getHospital().getName());
			appointmentInfo.setRemarks(appointment.getRemarks());
			appointmentInfo.setMedicalRecords(appointment.getMedicalRecords());
			appointmentInfo.setApproved(appointment.getApproved());
			appointmentInfoList.add(appointmentInfo);
		}));
		return appointmentInfoList;
	}

	@Transactional
	public List<AppointmentInfo> getAllAppointmentsByFacility(long hospitalFacilityId) {
		hospitalFacilityRepository.findById(hospitalFacilityId)
				.orElseThrow(() -> new HospitalFacilityNotFoundException("Invalid Facility"));
		List<Appointment> appointments = appointmentRepository
				.findByHospitalFacility_HospitalFacilityId(hospitalFacilityId);
		List<AppointmentInfo> appointmentInfoList = new ArrayList<AppointmentInfo>();
		appointments.forEach((appointment -> {
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentId(appointment.getAppointmentId());
			appointmentInfo.setPatientName(appointment.getPatient().getUser().getFirstName() + " "
					+ appointment.getPatient().getUser().getLastName());
			appointmentInfo.setAppointmentDate(appointment.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointment.getAppointmentTime());
			appointmentInfo.setFacilityName(appointment.getHospitalFacility().getFacility().getName());
			appointmentInfo.setHospitalName(appointment.getHospital().getName());
			appointmentInfo.setRemarks(appointment.getRemarks());
			appointmentInfo.setMedicalRecords(appointment.getMedicalRecords());
			appointmentInfo.setApproved(appointment.getApproved());
			appointmentInfoList.add(appointmentInfo);
		}));
		return appointmentInfoList;
	}

	@Transactional
	public String approveAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException("No such appointment"));
		appointment.setApproved(true);
		appointmentRepository.save(appointment);
		return "approved";
	}

	@Transactional
	public String rejectAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new AppointmentNotFoundException("No such appointment"));
		appointment.setApproved(false);
		appointmentRepository.save(appointment);
		return "rejected";
	}

}
