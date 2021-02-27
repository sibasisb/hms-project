package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.TreatmentHistoryInfo;
import com.project.hospitalmanagementbackend.exception.DoctorNotFoundException;
import com.project.hospitalmanagementbackend.exception.PatientNotFoundException;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.TreatmentHistory;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;
import com.project.hospitalmanagementbackend.repository.TreatmentHistoryRepository;

@SpringBootTest
public class TreatmentServiceTest {
	
	@InjectMocks
	TreatmentService treatmentService;
	
	@Mock
	TreatmentHistoryRepository treatmentHistoryRepository;
	
	@Mock
	PatientRepository patientRepository;
	
	@Mock
	DoctorRepository doctorRepository;

	@Test
	void updateTreatmentHistorySuccessTest()
	{
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		TreatmentHistory treatmentHistory = new TreatmentHistory(1l,"Mild fever and cough.Take Calpol",null,null);
		when(patientRepository.findById("PAT001")).thenReturn(Optional.of(patient));
		when(doctorRepository.findById("DOC001")).thenReturn(Optional.of(doctor));
		when(treatmentHistoryRepository.save(treatmentHistory)).thenReturn(treatmentHistory);
		String success="Treatment history updated successfully";
		assertEquals(success,treatmentService.updateTreatmentHistory("PAT001","DOC001",treatmentHistory));
	}
	
	@Test
	void updateTreatmentHistoryFailureTest1()
	{
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		TreatmentHistory treatmentHistory = new TreatmentHistory(1l,"Mild fever and cough.Take Calpol",null,null);
		when(patientRepository.findById("PAT001")).thenReturn(Optional.of(patient));
		when(doctorRepository.findById("DOC001")).thenReturn(Optional.of(doctor));
		when(treatmentHistoryRepository.save(treatmentHistory)).thenReturn(treatmentHistory);
		String success="Treatment history updated successfully";
		assertThrows(PatientNotFoundException.class,()->treatmentService.updateTreatmentHistory("PAT002","DOC001",treatmentHistory));
	}
	
	@Test
	void updateTreatmentHistoryFailureTest2()
	{
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		TreatmentHistory treatmentHistory = new TreatmentHistory(1l,"Mild fever and cough.Take Calpol",null,null);
		when(patientRepository.findById("PAT001")).thenReturn(Optional.of(patient));
		when(doctorRepository.findById("DOC001")).thenReturn(Optional.of(doctor));
		when(treatmentHistoryRepository.save(treatmentHistory)).thenReturn(treatmentHistory);
		String success="Treatment history updated successfully";
		assertThrows(DoctorNotFoundException.class,()->treatmentService.updateTreatmentHistory("PAT002","DOC002",treatmentHistory));
	}

	@Test
	void getAllTreatmentHistorySuccessTest()
	{

		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		TreatmentHistory treatmentHistory = new TreatmentHistory(1l,"Mild fever.Take calpol",patient,doctor);
		ArrayList<TreatmentHistory> list = new ArrayList<TreatmentHistory>();
		list.add(treatmentHistory);
		when(patientRepository.findById("PAT001")).thenReturn(Optional.of(patient));
		when(treatmentHistoryRepository.findByPatient_PatientId("PAT001")).thenReturn(list);
		TreatmentHistoryInfo treatmentHistoryInfo = new TreatmentHistoryInfo("John Doe",35,"male","Munna Bhai","cardio","Mild fever.Take calpol");
		ArrayList<TreatmentHistoryInfo> historyList = new ArrayList<TreatmentHistoryInfo>();
		historyList.add(treatmentHistoryInfo);
		assertEquals(historyList.size(),treatmentService.getAllTreatmentHistory("PAT001").size());
		
	
	}
	
	@Test
	void getAllTreatmentHistoryFailureTest()
	{

		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		TreatmentHistory treatmentHistory = new TreatmentHistory(1l,"Mild fever.Take calpol",patient,doctor);
		ArrayList<TreatmentHistory> list = new ArrayList<TreatmentHistory>();
		list.add(treatmentHistory);
		when(patientRepository.findById("PAT001")).thenReturn(Optional.of(patient));
		when(treatmentHistoryRepository.findByPatient_PatientId("PAT001")).thenReturn(list);
		TreatmentHistoryInfo treatmentHistoryInfo = new TreatmentHistoryInfo("John Doe",35,"male","Munna Bhai","cardio","Mild fever.Take calpol");
		ArrayList<TreatmentHistoryInfo> historyList = new ArrayList<TreatmentHistoryInfo>();
		historyList.add(treatmentHistoryInfo);
		assertThrows(PatientNotFoundException.class,()->treatmentService.getAllTreatmentHistory("PAT002"));
	
	}
	
	@Test
	void getTreatmentHistorySuccessTest()
	{

		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		TreatmentHistory treatmentHistory = new TreatmentHistory(1l,"Mild fever.Take calpol",patient,doctor);
		when(patientRepository.findById("PAT001")).thenReturn(Optional.of(patient));
		when(doctorRepository.findById("DOC001")).thenReturn(Optional.of(doctor));
		when(treatmentHistoryRepository.findByPatient_PatientIdAndDoctor_DoctorId("PAT001","DOC001")).thenReturn(treatmentHistory);
		TreatmentHistoryInfo treatmentHistoryInfo = new TreatmentHistoryInfo("John Doe",35,"male","Munna Bhai","cardio","Mild fever.Take calpol");
		TreatmentHistoryInfo treatmentHistoryInfo2 = treatmentService.getTreatmentHistory("PAT001","DOC001");
		assertEquals(treatmentHistoryInfo.getPatientName(),treatmentHistoryInfo.getPatientName());
		
	
	}
	
	@Test
	void getTreatmentHistoryFailureTest1()
	{

		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		TreatmentHistory treatmentHistory = new TreatmentHistory(1l,"Mild fever.Take calpol",patient,doctor);
		when(patientRepository.findById("PAT001")).thenReturn(Optional.of(patient));
		when(doctorRepository.findById("DOC001")).thenReturn(Optional.of(doctor));
		when(treatmentHistoryRepository.findByPatient_PatientIdAndDoctor_DoctorId("PAT001","DOC001")).thenReturn(treatmentHistory);
		TreatmentHistoryInfo treatmentHistoryInfo = new TreatmentHistoryInfo("John Doe",35,"male","Munna Bhai","cardio","Mild fever.Take calpol");
		TreatmentHistoryInfo treatmentHistoryInfo2 = treatmentService.getTreatmentHistory("PAT001","DOC001");
		assertThrows(DoctorNotFoundException.class,()->treatmentService.getTreatmentHistory("PAT001","DOC002"));
	}
	
	@Test
	void getTreatmentHistoryFailureTest2()
	{

		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		TreatmentHistory treatmentHistory = new TreatmentHistory(1l,"Mild fever.Take calpol",patient,doctor);
		when(patientRepository.findById("PAT001")).thenReturn(Optional.of(patient));
		when(doctorRepository.findById("DOC001")).thenReturn(Optional.of(doctor));
		when(treatmentHistoryRepository.findByPatient_PatientIdAndDoctor_DoctorId("PAT001","DOC001")).thenReturn(treatmentHistory);
		TreatmentHistoryInfo treatmentHistoryInfo = new TreatmentHistoryInfo("John Doe",35,"male","Munna Bhai","cardio","Mild fever.Take calpol");
		TreatmentHistoryInfo treatmentHistoryInfo2 = treatmentService.getTreatmentHistory("PAT001","DOC001");
		assertThrows(PatientNotFoundException.class,()->treatmentService.getTreatmentHistory("PAT002","DOC001"));
		
	
	}
	
	
}

