package com.project.hospitalmanagementbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.dto.PatientInfo;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@GetMapping("/doc/{doctorId}")
	public ResponseEntity<?> getPatientsByDoctorId(@PathVariable("doctorId") String doctorId){
		List<Patient> patientList=patientService.findPatientsFromDoctorId(doctorId);
		List<PatientInfo> patientInfoList=new ArrayList<PatientInfo>();
		for(Patient p:patientList) {
			PatientInfo patInfo=new PatientInfo();
			patInfo.setPatientId(p.getPatientId());
			patInfo.setFirstName(p.getUser().getFirstName());
			patInfo.setLastName(p.getUser().getLastName());
			patInfo.setDateOfBirth(p.getUser().getDateOfBirth());
			patInfo.setContact(p.getUser().getContact());
			patInfo.setGender(p.getUser().getGender());
			patientInfoList.add(patInfo);
		}
		return new ResponseEntity<>(patientInfoList, HttpStatus.OK);
	}
	
	@GetMapping("/pat/{patientId}")
	public ResponseEntity<?> getPatientByPatientId(@PathVariable("patientId") String patientId){
		PatientInfo patientInfo=patientService.findPatientByPatientId(patientId);
		return new ResponseEntity<>(patientInfo, HttpStatus.OK);
	}
	
}
