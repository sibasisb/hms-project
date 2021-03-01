package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.InPatientInfo;
import com.project.hospitalmanagementbackend.exception.InPatientNotFoundException;
import com.project.hospitalmanagementbackend.exception.PatientNotFoundException;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.InPatient;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.repository.InPatientRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@Service
public class InPatientService {
	
	@Autowired
	InPatientRepository inPatientRepository;
	
	@Autowired
	HospitalService hospitalService;
	
	//change
	@Autowired
	PatientRepository patientRepository;
	
	public List<InPatientInfo> getInPatientsByHospital(String hospitalId){
		
		List<InPatient> inPatientList = inPatientRepository.findInPatientByHospital(hospitalId);
		List<InPatientInfo> inPatientInfoList = new ArrayList<>();
		inPatientList.forEach((inPatient->{
			InPatientInfo inPatientInfo = new InPatientInfo();
			inPatientInfo.setInPatientId(inPatient.getInPatientId());
			inPatientInfo.setPatientId(inPatient.getPatient().getPatientId());
			inPatientInfo.setFirstName(inPatient.getPatient().getUser().getFirstName());
			inPatientInfo.setLastName(inPatient.getPatient().getUser().getLastName());
			inPatientInfo.setGender(inPatient.getPatient().getUser().getGender());
			inPatientInfo.setAdmissionDate(inPatient.getAdmissionDate());
			inPatientInfo.setAdmissionTime(inPatient.getAdmissionTime());
			inPatientInfo.setDischargeTime(inPatient.getDischargeTime());
			inPatientInfo.setDischargeDate(inPatient.getDischargeDate());
			inPatientInfo.setRoomCharges(inPatient.getRoomCharges());
			inPatientInfo.setPaid(inPatient.isPaid());
			inPatientInfoList.add(inPatientInfo);
			
		}));
		return inPatientInfoList;
	}
	
	public String addInPatient(InPatient inPatient,String hospitalId,String patientId) {
		
		Hospital hospital = hospitalService.getHospitalById(hospitalId);
		Patient patient = patientRepository.findById(patientId).orElseThrow(
				() -> new PatientNotFoundException("Patient Not found"));
		inPatient.setHospital(hospital);
		inPatient.setPatient(patient);
		 inPatientRepository.save(inPatient);
		 return "InPatient added successfully";
	}
	
	
	public String updateInPatient(InPatient inPatient,String hospitalId,String patientId) {
		
		Optional<InPatient> findPatient = inPatientRepository.findById(inPatient.getInPatientId());
		if(findPatient.isPresent())
		{
			Hospital hospital = hospitalService.getHospitalById(hospitalId);
			Patient patient = patientRepository.findById(patientId).get();
			inPatient.setHospital(hospital);
			inPatient.setPatient(patient);
			inPatientRepository.save(inPatient);
			return "Patient details updated !!";
			
		}
		else
			throw new InPatientNotFoundException("Patient Not Found");
		
		
	}

}
