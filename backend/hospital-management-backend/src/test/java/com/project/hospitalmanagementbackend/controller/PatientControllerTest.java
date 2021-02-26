package com.project.hospitalmanagementbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.dto.PatientInfo;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.service.PatientService;

@SpringBootTest
public class PatientControllerTest {

	@InjectMocks
	private PatientController patientController;

	@Mock
	private PatientService patientService;

	@Test
	public void testGetPatientsByDoctorId() {
		Patient patient = new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.parse("1985-05-25"), "Male",
				"7894561230", "john@doe.com", "incorrect", "patient"));
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(patient);
		String doctorId = "DOC000001";
		when(patientService.findPatientsFromDoctorId(doctorId)).thenReturn(patientList);
		List<PatientInfo> patientInfoList = new ArrayList<PatientInfo>();
		Patient p = patientList.get(0);
		PatientInfo patInfo = new PatientInfo();
		patInfo.setPatientId(p.getPatientId());
		patInfo.setFirstName(p.getUser().getFirstName());
		patInfo.setLastName(p.getUser().getLastName());
		patInfo.setDateOfBirth(p.getUser().getDateOfBirth());
		patInfo.setContact(p.getUser().getContact());
		patInfo.setGender(p.getUser().getGender());
		patientInfoList.add(patInfo);
		ResponseEntity<List<PatientInfo>> patientsByDoctorId = (ResponseEntity<List<PatientInfo>>)patientController.getPatientsByDoctorId(doctorId);
		assertEquals(new ResponseEntity<>(patientInfoList, HttpStatus.OK).getBody().size(),
				patientsByDoctorId.getBody().size());
	}

}
