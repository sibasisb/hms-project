package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.PatientInfo;
import com.project.hospitalmanagementbackend.exception.PatientNotFoundException;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@SpringBootTest
public class PatientServiceTest {

	@InjectMocks
	private PatientService patientService;
	
	@Mock
	private PatientRepository patientRepository;
	
	@Mock
	private AppointmentRepository appointmentRepository;
	
	@Test
	public void testFindPatientsFromDoctorId() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		List<Appointment> appointmentList=new ArrayList<Appointment>();
		appointmentList.add(appointment);
		String doctorId="DOC000001";
		List<Patient> patientList=new ArrayList<Patient>();
		patientList.add(patient);
		when(appointmentRepository.findAppointmentsByDoctorId(doctorId)).thenReturn(appointmentList);
		assertEquals(patientService.findPatientsFromDoctorId(doctorId), patientList);
	}
	
	@Test
	public void testFindPatientByPatientIdFailure() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		when(patientRepository.findById(patient.getPatientId())).thenReturn(Optional.empty());
		assertThrows(PatientNotFoundException.class,()->patientService.findPatientByPatientId(patient.getPatientId()));
	}
	
	@Test
	public void testFindPatientByPatientId() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Optional<Patient> poption=Optional.of(patient);
		Patient p=poption.get();
		PatientInfo patInfo=new PatientInfo();
		patInfo.setPatientId(p.getPatientId());
		patInfo.setFirstName(p.getUser().getFirstName());
		patInfo.setLastName(p.getUser().getLastName());
		patInfo.setDateOfBirth(p.getUser().getDateOfBirth());
		patInfo.setContact(p.getUser().getContact());
		patInfo.setGender(p.getUser().getGender());
		when(patientRepository.findById(patient.getPatientId())).thenReturn(poption);
		assertEquals(patInfo.getFirstName(),patientService.findPatientByPatientId(patient.getPatientId()).getFirstName());
	}
	
}
