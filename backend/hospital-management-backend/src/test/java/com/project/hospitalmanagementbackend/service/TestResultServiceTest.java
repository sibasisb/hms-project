package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.model.TestResultsInformation;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;
import com.project.hospitalmanagementbackend.repository.TestResultRepository;

@SpringBootTest
public class TestResultServiceTest {

	@InjectMocks
	private TestResultService testResultService;
	
	@Mock
	private TestResultRepository testResultRepository;
	
	@Mock
	private AppointmentRepository appointmentRepository;
	
	@Mock
	private PatientRepository patientRepository;
	
	@Test
	public void testGetTestResults() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, appointment,null);
		testResult.setInfos(new ArrayList<TestResultsInformation>());
		Set<TestResult> testResultList=new HashSet<TestResult>();
		testResultList.add(testResult);
		when(testResultRepository.getTestResults(patient.getPatientId(),appointment.getAppointmentId())).thenReturn(testResultList);
		assertEquals(testResultList.size(),testResultService.getTestResults(patient.getPatientId(),appointment.getAppointmentId()).size());
	}
	
	@Test
	public void testUpdateTestResult() {
		String res="Updated record successfully";
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, appointment,null);
		testResult.setInfos(new ArrayList<TestResultsInformation>());
		when(testResultRepository.findById(testResult.getResultId())).thenReturn(Optional.of(testResult));
		when(appointmentRepository.findById(appointment.getAppointmentId())).thenReturn(Optional.of(appointment));
		when(patientRepository.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		when(testResultRepository.save(testResult)).thenReturn(testResult);
		assertEquals(res,testResultService.updateTestResult(testResult,patient.getPatientId(),appointment.getAppointmentId(),testResult.getResultId()));
	}
	
	@Test
	public void testAddTestResult() {
		String res="Added record successfully";
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, appointment,null);
		testResult.setInfos(new ArrayList<TestResultsInformation>());
		when(testResultRepository.findById(testResult.getResultId())).thenReturn(Optional.of(testResult));
		when(appointmentRepository.findById(appointment.getAppointmentId())).thenReturn(Optional.of(appointment));
		when(patientRepository.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		when(testResultRepository.save(testResult)).thenReturn(testResult);
		assertEquals(res,testResultService.addTestResult(testResult,patient.getPatientId(),appointment.getAppointmentId()));
	}
	
	@Test
	public void testFetchTestResultsAndDetails() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, appointment,null);
		testResult.setInfos(new ArrayList<TestResultsInformation>());
		Set<TestResult> testResultList=new HashSet<TestResult>();
		testResultList.add(testResult);
		when(testResultRepository.getTestResultsByPatientId(patient.getPatientId())).thenReturn(testResultList);
		assertEquals(testResultList.size(),testResultService.fetchTestResultsAndDetails(patient.getPatientId()).size());
	}
	
}
