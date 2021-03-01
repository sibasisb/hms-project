package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.Period;
import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.TreatmentHistoryInfo;
import com.project.hospitalmanagementbackend.exception.DoctorNotFoundException;
import com.project.hospitalmanagementbackend.exception.PatientNotFoundException;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.model.TreatmentHistory;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;
import com.project.hospitalmanagementbackend.repository.TreatmentHistoryRepository;

@Transactional
@Service
public class TreatmentService {

	@Autowired
	TreatmentHistoryRepository treatmentHistoryRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DoctorRepository doctorRepository;

	public String updateTreatmentHistory(String patientid, String doctorId, TreatmentHistory treatmentHistory) {
		// TODO Auto-generated method stub
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		Optional<Patient> patient = patientRepository.findById(patientid);
		Optional<TreatmentHistory> history = treatmentHistoryRepository.findById(treatmentHistory.getTreatmentId());
		if(!history.isPresent())
		{
		
		if (doctor.isPresent())
			treatmentHistory.setDoctor(doctor.get());
		else
			throw new DoctorNotFoundException("Doctor not found");
		
		if (patient.isPresent())
			treatmentHistory.setPatient(patient.get());
		else
			throw new PatientNotFoundException("Patient not found");
		
		treatmentHistoryRepository.save(treatmentHistory);
		}
		else
		{
			TreatmentHistory treatmentHistoryFound = history.get();
			treatmentHistoryFound.setPrescription(treatmentHistory.getPrescription());
			treatmentHistoryRepository.save(treatmentHistoryFound);
		}
		return "Treatment history updated successfully";
	}

	public List<TreatmentHistoryInfo> getAllTreatmentHistory(String patientId) {
		// TODO Auto-generated method stub
		Optional<Patient> optional = patientRepository.findById(patientId);
		if (optional.isPresent()) {
			List<TreatmentHistory> treatmentHistorylist = treatmentHistoryRepository.findByPatient_PatientId(patientId);
			ArrayList<TreatmentHistoryInfo> historyList = new ArrayList<TreatmentHistoryInfo>();
			treatmentHistorylist.stream().forEach((history) -> {
				TreatmentHistoryInfo historyInfo = new TreatmentHistoryInfo();
				User patientInfo = history.getPatient().getUser();
				Doctor doctor = history.getDoctor();
				historyInfo.setTreatmentId(history.getTreatmentId());
				historyInfo.setPatientName(patientInfo.getFirstName() + " " + patientInfo.getLastName());
				historyInfo.setAge(Period.between(patientInfo.getDateOfBirth(), LocalDate.now()).getYears());
				historyInfo.setGender(patientInfo.getGender());
				historyInfo.setDoctorName(doctor.getUser().getFirstName() + " " + doctor.getUser().getLastName());
				historyInfo.setDoctorSpecilaity(doctor.getSpeciality());
				historyInfo.setPrescription(history.getPrescription());
				historyList.add(historyInfo);
			});

			return historyList;
		} else
			throw new PatientNotFoundException("Patient not found");

	}

	public TreatmentHistoryInfo getTreatmentHistory(String patientId, String doctorId) {
		// TODO Auto-generated method stub
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (!doctor.isPresent())
			throw new DoctorNotFoundException("Doctor not found");
		Optional<Patient> patient = patientRepository.findById(patientId);
		if (!patient.isPresent())
			throw new PatientNotFoundException("Patient not found");

		TreatmentHistory history = treatmentHistoryRepository.findByPatient_PatientIdAndDoctor_DoctorId(patientId, doctorId);
		if(history!=null)
		{
		TreatmentHistoryInfo historyInfo = new TreatmentHistoryInfo();
		User patientInfo = history.getPatient().getUser();
		Doctor doctorInfo = history.getDoctor();
		historyInfo.setTreatmentId(history.getTreatmentId());
		historyInfo.setPatientName(patientInfo.getFirstName() + " " + patientInfo.getLastName());
		historyInfo.setAge(Period.between(patientInfo.getDateOfBirth(), LocalDate.now()).getYears());
		historyInfo.setGender(patientInfo.getGender());
		historyInfo.setDoctorName(doctorInfo.getUser().getFirstName() + " " + doctorInfo.getUser().getLastName());
		historyInfo.setDoctorSpecilaity(doctorInfo.getSpeciality());
		historyInfo.setPrescription(history.getPrescription());
		return historyInfo;
		}
		
		return null;
		

	}

}
