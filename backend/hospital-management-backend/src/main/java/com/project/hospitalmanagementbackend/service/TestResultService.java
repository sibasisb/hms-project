package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.TestResultDto;
import com.project.hospitalmanagementbackend.exception.AppointmentNotFoundException;
import com.project.hospitalmanagementbackend.exception.PatientNotFoundException;
import com.project.hospitalmanagementbackend.exception.TestResultNotFoundException;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.model.TestResultsInformation;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;
import com.project.hospitalmanagementbackend.repository.TestResultRepository;

@Service
public class TestResultService {

	@Autowired
	private TestResultRepository testResultRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Transactional
	public Set<TestResultDto> getTestResults(String patientId,Long appointmentId){
		Set<TestResult> testResults = testResultRepository.getTestResults(patientId,appointmentId);
		Set<TestResultDto> testInfoList=new HashSet<TestResultDto>();
		testResults.forEach((result)->{
			TestResultDto testResultDto=new TestResultDto();
			testResultDto.setAppointmentId(result.getAppointment().getAppointmentId());
			testResultDto.setPatientName(result.getPatient().getUser().getFirstName() +" " + result.getPatient().getUser().getLastName());
			testResultDto.setPatientId(result.getPatient().getPatientId());
			testResultDto.setTestName(result.getTestName());
			//List<TestResultsInformation> testResultsInformationList=new ArrayList<>();
			HashMap<String,String> hmap=new HashMap<>();
			result.getInfos().forEach((info)->{
				/*
				 * TestResultsInformation testResultInformation=new TestResultsInformation();
				 * testResultInformation.setResultInfoName(info.getResultInfoName());
				 * testResultInformation.setResultInfoValue(info.getResultInfoValue());
				 * testResultInformation.setTestResult(result);
				 * testResultsInformationList.add(testResultInformation);
				 */
				hmap.put(info.getResultInfoName(),info.getResultInfoValue());
			});
			testResultDto.setInfos(hmap);
			testResultDto.setResultId(result.getResultId());
			testInfoList.add(testResultDto);
		});
		return testInfoList;
	}
	
	@Transactional
	public String updateTestResult(TestResult testResult,String patientId,Long appointmentId,long resultId) {
		if(!testResultRepository.findById(resultId).isPresent()) {
			throw new TestResultNotFoundException("Test result not found");
		}
		Optional<Patient> oppatient=patientRepository.findById(patientId);
		if(!oppatient.isPresent()) {
			throw new PatientNotFoundException("Patient not found");
		}
		Optional<Appointment> opappointment=appointmentRepository.findById(appointmentId);
		if(!opappointment.isPresent()) {
			//TODO:add exception
			throw new PatientNotFoundException("Patient not found");
		}
		Patient patient=oppatient.get();
		Appointment appointment=opappointment.get();
		testResult.setAppointment(appointment);
		testResult.setPatient(patient);
		List<TestResultsInformation> testResultsInformationList=new ArrayList<>();
		testResult.getInfos().forEach((info)->{
			TestResultsInformation testResultInformation=new TestResultsInformation();
			testResultInformation.setResultInfoName(info.getResultInfoName());
			testResultInformation.setResultInfoValue(info.getResultInfoValue());
			testResultInformation.setTestResult(testResult);
			testResultsInformationList.add(testResultInformation);
		});
		testResult.setResultId(resultId);
		testResult.setInfos(testResultsInformationList);
		testResult.setTestName(testResult.getTestName());
		TestResult testResultObj=testResultRepository.save(testResult);
		if(testResultObj!=null)
			return "Updated record successfully";
		return "Invalid update";
	}

	public Set<TestResultDto> fetchTestResultsAndDetails(String patientId) {
		Set<TestResult> testResults = testResultRepository.getTestResultsByPatientId(patientId);
		Set<TestResultDto> testInfoList=new HashSet<TestResultDto>();
		testResults.forEach((result)->{
			TestResultDto testResultDto=new TestResultDto();
			testResultDto.setAppointmentId(result.getAppointment().getAppointmentId());
			testResultDto.setPatientName(result.getPatient().getUser().getFirstName() +" " + result.getPatient().getUser().getLastName());
			testResultDto.setPatientId(result.getPatient().getPatientId());
			testResultDto.setTestName(result.getTestName());
			//List<TestResultsInformation> testResultsInformationList=new ArrayList<>();
			HashMap<String,String> hmap=new HashMap<>();
			result.getInfos().forEach((info)->{
				/*
				 * TestResultsInformation testResultInformation=new TestResultsInformation();
				 * testResultInformation.setResultInfoName(info.getResultInfoName());
				 * testResultInformation.setResultInfoValue(info.getResultInfoValue());
				 * testResultInformation.setTestResult(result);
				 * testResultsInformationList.add(testResultInformation);
				 */
				hmap.put(info.getResultInfoName(),info.getResultInfoValue());
			});
			testResultDto.setInfos(hmap);
			testResultDto.setResultId(result.getResultId());
			testInfoList.add(testResultDto);
		});
		return testInfoList;
	}

	public String addTestResult(TestResult testResult,String patientId,Long appointmentId) {
		Optional<Patient> oppatient=patientRepository.findById(patientId);
		if(!oppatient.isPresent()) {
			throw new PatientNotFoundException("Patient not found");
		}
		Optional<Appointment> opappointment=appointmentRepository.findById(appointmentId);
		if(!opappointment.isPresent()) {
			//TODO:add exception
			throw new AppointmentNotFoundException("Appointment not found");
		}
		Patient patient=oppatient.get();
		Appointment appointment=opappointment.get();
		testResult.setAppointment(appointment);
		testResult.setPatient(patient);
		List<TestResultsInformation> testResultsInformationList=new ArrayList<>();
		
		testResult.getInfos().forEach((info)->{
			TestResultsInformation testResultInformation=new TestResultsInformation();
			testResultInformation.setResultInfoName(info.getResultInfoName());
			testResultInformation.setResultInfoValue(info.getResultInfoValue());
			testResultInformation.setTestResult(testResult);
			testResultsInformationList.add(testResultInformation);
		});
		testResult.setInfos(testResultsInformationList);
		testResult.setTestName(testResult.getTestName());
		TestResult testResultObj=testResultRepository.save(testResult);
		if(testResultObj!=null)
			return "Added record successfully";
		return "Invalid add";
	}

	public TestResultDto getTestResultByIdService(long testResultId) {
		TestResult result = testResultRepository.findByTestResultId(testResultId);
		if(result==null) {
			throw new TestResultNotFoundException("Test result not found");
		}
		TestResultDto testResultDto=new TestResultDto();
		testResultDto.setAppointmentId(result.getAppointment().getAppointmentId());
		testResultDto.setPatientName(result.getPatient().getUser().getFirstName() +" " + result.getPatient().getUser().getLastName());
		testResultDto.setPatientId(result.getPatient().getPatientId());
		testResultDto.setTestName(result.getTestName());
		HashMap<String,String> hmap=new HashMap<>();
		result.getInfos().forEach((info)->{
			hmap.put(info.getResultInfoName(),info.getResultInfoValue());
		});
		testResultDto.setInfos(hmap);
		testResultDto.setResultId(result.getResultId());
		return testResultDto;
	}
	
	public TestResultDto fetchTestInfo(long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).get();
		TestResultDto testInfo=new TestResultDto();
		User userInfo = appointment.getPatient().getUser();
		testInfo.setPatientId(appointment.getPatient().getPatientId());
		testInfo.setPatientName(userInfo.getFirstName()+" "+userInfo.getLastName());
		Facility facilityInfo=appointment.getHospitalFacility().getFacility();
		testInfo.setTestName(facilityInfo.getName());
			HashMap<String,String> hmap=new HashMap<>();
			facilityInfo.getBaselines().forEach((baseline)->{
				hmap.put(baseline.getBaselineName(),"");
			});
			testInfo.setInfos(hmap);
			
		return testInfo;
	}
	
}
