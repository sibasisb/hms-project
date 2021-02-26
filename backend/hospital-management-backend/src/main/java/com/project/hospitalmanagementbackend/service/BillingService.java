package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.BillingInfo;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.InPatient;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.InPatientRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@Service
public class BillingService {

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	InPatientRepository inPatientRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	PatientRepository patientRepository;

	public List<BillingInfo> generateBillByPatientId(String hospitalId, String patientId) {

		if(!hospitalRepository.getHospitalById(hospitalId).isPresent())
			throw new RuntimeException("Hospital Not Found");
		
		if(!patientRepository.findById(patientId).isPresent())
			throw new RuntimeException("Patient Not Found");
		
		List<Appointment> appointments = appointmentRepository.findUnpaidAppointments(hospitalId, patientId);
		List<InPatient> inPatients = inPatientRepository.findUnpaidInpatients(hospitalId, patientId);

		List<BillingInfo> billingInfoList = new ArrayList<BillingInfo>();
		appointments.forEach((appointment -> {
			BillingInfo billingInfo = new BillingInfo();
			billingInfo.setPatientId(appointment.getPatient().getPatientId());
			billingInfo.setPatientName(appointment.getPatient().getUser().getFirstName()
					+ appointment.getPatient().getUser().getLastName());
			if (appointment.getDoctor() != null) {
				billingInfo.setDoctorName(appointment.getDoctor().getUser().getFirstName()
						+ appointment.getDoctor().getUser().getLastName());
				billingInfo.setDoctorCharge(appointment.getDoctor().getCharge());
			}

			else {
				billingInfo.setFacilityName(appointment.getHospitalFacility().getFacility().getName());
				billingInfo.setFacilityCharge(appointment.getHospitalFacility().getCharges());
			}
			billingInfoList.add(billingInfo);
		}));

		inPatients.forEach((inPatient -> {
			BillingInfo billingInfo = new BillingInfo();
			billingInfo.setPatientId(inPatient.getPatient().getPatientId());
			billingInfo.setPatientName(
					inPatient.getPatient().getUser().getFirstName() + inPatient.getPatient().getUser().getLastName());
			billingInfo.setAdmissionDate(inPatient.getAdmissionDate());
			billingInfo.setDischargeDate(inPatient.getDischargeDate());
			billingInfo.setRoomCharges(inPatient.getRoomCharges());
			billingInfoList.add(billingInfo);
		}));

		return billingInfoList;

	}

}
