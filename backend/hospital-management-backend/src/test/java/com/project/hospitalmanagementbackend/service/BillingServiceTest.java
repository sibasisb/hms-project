package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.BillingInfo;
import com.project.hospitalmanagementbackend.exception.HospitalNotFoundException;
import com.project.hospitalmanagementbackend.exception.PatientNotFoundException;
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
	
	@Test
	void testGenerateBillByPatientIdFaliure1() {
		
		Patient patient = new Patient();
		Hospital hospital = new Hospital();
		List<Appointment> appointments = new ArrayList<Appointment>();
		List<InPatient> inPatients = new ArrayList<InPatient>();
		when(patientRepository.findById("pId")).thenReturn(Optional.of(patient));
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		when(appointmentRepository.findUnpaidAppointments("hId", "pId")).thenReturn(appointments);
		when(inPatientRepository.findUnpaidInpatients("hId", "pId")).thenReturn(inPatients);
		assertThrows(PatientNotFoundException.class, ()->billingService.generateBillByPatientId("hId", "ppyId"));
		
	}
	
	@Test
	void testGenerateBillByPatientIdFaliure2() {
		
		Patient patient = new Patient();
		Hospital hospital = new Hospital();
		List<Appointment> appointments = new ArrayList<Appointment>();
		List<InPatient> inPatients = new ArrayList<InPatient>();
		when(patientRepository.findById("pId")).thenReturn(Optional.of(patient));
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		when(appointmentRepository.findUnpaidAppointments("hId", "pId")).thenReturn(appointments);
		when(inPatientRepository.findUnpaidInpatients("hId", "pId")).thenReturn(inPatients);
		assertThrows(HospitalNotFoundException.class, ()->billingService.generateBillByPatientId("hhhId", "pId"));
		
	}
	
	@Test
	void testPayBillByPatient() {
		
		Patient patient = new Patient();
		Hospital hospital = new Hospital();
		String msg="Bills Paid Successfully!!";
		when(patientRepository.findById("pId")).thenReturn(Optional.of(patient));
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		doNothing().when(appointmentRepository).payForAppointments("hId", "pId");
		doNothing().when(inPatientRepository).payForInpatients("hId", "pId");
		assertEquals(msg, billingService.payPatientBill("hId", "pId"));
		
	}
	
	@Test
	void testPayBillByPatientFaliure1() {
		
		Patient patient = new Patient();
		Hospital hospital = new Hospital();
		when(patientRepository.findById("pId")).thenReturn(Optional.of(patient));
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		doNothing().when(appointmentRepository).payForAppointments("hId", "pId");
		doNothing().when(inPatientRepository).payForInpatients("hId", "pId");
		assertThrows(PatientNotFoundException.class, ()->billingService.payPatientBill("hId", "pppId"));
		
	}
	
	@Test
	void testPayBillByPatientFaliure2() {
		
		Patient patient = new Patient();
		Hospital hospital = new Hospital();
		when(patientRepository.findById("pId")).thenReturn(Optional.of(patient));
		when(hospitalRepository.getHospitalById("hId")).thenReturn(Optional.of(hospital));
		doNothing().when(appointmentRepository).payForAppointments("hId", "pId");
		doNothing().when(inPatientRepository).payForInpatients("hId", "pId");
		assertThrows(HospitalNotFoundException.class, ()->billingService.payPatientBill("hhId", "pId"));
		
	}

}
