package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.BillingInfo;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.InPatient;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.InPatientRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@SpringBootTest
public class BillingServiceTest {
	
	@InjectMocks
	BillingService billingService;
	
	@Mock
	AppointmentRepository appointmentRepository;
	
	@Mock
	InPatientRepository inPatientRepository;
	
	@Mock
	HospitalRepository hospitalRepository;
	
	@Mock
	PatientRepository patientRepository;
	
	@Test
	void testGenerateBillByPatientId() {
		
		Patient patient = new Patient();
		Hospital hospital = new Hospital();
		List<Appointment> appointments = new ArrayList<Appointment>();
		List<InPatient> inPatients = new ArrayList<InPatient>();
		List<BillingInfo> billingInfoList = new ArrayList<BillingInfo>();
		when(patientRepository.findById("pId")).thenReturn(Optional.of(patient));
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		when(appointmentRepository.findUnpaidAppointments("hId", "pId")).thenReturn(appointments);
		when(inPatientRepository.findUnpaidInpatients("hId", "pId")).thenReturn(inPatients);
		assertEquals(billingInfoList, billingService.generateBillByPatientId("hId", "pId"));
		
	}

}
