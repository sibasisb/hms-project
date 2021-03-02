package com.project.hospitalmanagementbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.dto.TestResultDto;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Baseline;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.TestResult;
import com.project.hospitalmanagementbackend.model.TestResultsInformation;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.service.TestResultService;

@SpringBootTest
public class TestResultControllerTest {

	@InjectMocks
	private TestResultController testResultController;
	
	@Mock
	private TestResultService testResultService;
	
	@Test
	public void testGetTestResults() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, appointment,null);
		Set<TestResult> testResults=new HashSet<TestResult>();
		testResults.add(testResult);
		Set<TestResultDto> testInfoList=new HashSet<TestResultDto>();
		testResults.forEach((result)->{
			TestResultDto testResultDto=new TestResultDto();
			testResultDto.setPatientName(result.getPatient().getUser().getFirstName() +" " + result.getPatient().getUser().getLastName());
			testResultDto.setPatientId(result.getPatient().getPatientId());
			testResultDto.setTestName(result.getTestName());
			//List<TestResultsInformation> testResultsInformationList=new ArrayList<>();
			HashMap<String,String> hmap=new HashMap<>();
			if(result.getInfos()==null) {
				result.setInfos(new ArrayList<TestResultsInformation>());
			}
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
		when(testResultService.getTestResults(patient.getPatientId(),appointment.getAppointmentId())).thenReturn(testInfoList);
		ResponseEntity<Set<TestResultDto>> obj =(ResponseEntity<Set<TestResultDto>>) testResultController.getTestResults(appointment.getAppointmentId(),patient.getPatientId());
		assertEquals(obj.getBody().size(), new ResponseEntity<>(testInfoList, HttpStatus.OK).getBody().size());
	}
	
	@Test
	public void testGetTestResultByIdController() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResult result=new TestResult(1L, "Blood Test", patient, appointment,null);
		TestResultDto testResultDto=new TestResultDto();
		testResultDto.setPatientName(result.getPatient().getUser().getFirstName() +" " + result.getPatient().getUser().getLastName());
		testResultDto.setPatientId(result.getPatient().getPatientId());
		testResultDto.setTestName(result.getTestName());
		//List<TestResultsInformation> testResultsInformationList=new ArrayList<>();
		HashMap<String,String> hmap=new HashMap<>();
		if(result.getInfos()==null) {
			result.setInfos(new ArrayList<TestResultsInformation>());
		}
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
		when(testResultService.getTestResultByIdService(result.getResultId())).thenReturn(testResultDto);
		assertEquals(testResultController.getTestResultById(result.getResultId()), new ResponseEntity<>(testResultDto,HttpStatus.OK));
	}
	
	@Test
	public void testUpdateTestResultController() {
		String res="Updated record successfully";
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, appointment,null);
		when(testResultService.updateTestResult(testResult,patient.getPatientId(),appointment.getAppointmentId(),testResult.getResultId())).thenReturn(res);
		assertEquals(testResultController.updateTestResults(testResult,appointment.getAppointmentId(),patient.getPatientId(),testResult.getResultId()), new ResponseEntity<>(res,HttpStatus.OK));
	}
	
	@Test
	public void testAddTestResultController() {
		String res="Updated record successfully";
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, appointment,null);
		when(testResultService.addTestResult(testResult,patient.getPatientId(),appointment.getAppointmentId())).thenReturn(res);
		assertEquals(testResultController.addTestResults(testResult,appointment.getAppointmentId(),patient.getPatientId()), new ResponseEntity<>(res,HttpStatus.OK));
	}
	
	@Test
	public void fetchTestResultsAndDetails() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResult testResult=new TestResult(1L, "Blood Test", patient, appointment,null);
		Set<TestResult> testResults=new HashSet<TestResult>();
		testResults.add(testResult);
		Set<TestResultDto> testInfoList=new HashSet<TestResultDto>();
		testResults.forEach((result)->{
			TestResultDto testResultDto=new TestResultDto();
			testResultDto.setPatientName(result.getPatient().getUser().getFirstName() +" " + result.getPatient().getUser().getLastName());
			testResultDto.setPatientId(result.getPatient().getPatientId());
			testResultDto.setTestName(result.getTestName());
			//List<TestResultsInformation> testResultsInformationList=new ArrayList<>();
			HashMap<String,String> hmap=new HashMap<>();
			if(result.getInfos()==null) {
				result.setInfos(new ArrayList<TestResultsInformation>());
			}
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
		when(testResultService.fetchTestResultsAndDetails(patient.getPatientId())).thenReturn(testInfoList);
		ResponseEntity<Set<TestResultDto>> obj =(ResponseEntity<Set<TestResultDto>>) testResultController.fetchTestResultsAndDetails(patient.getPatientId());
		assertEquals(obj.getBody().size(), new ResponseEntity<>(testInfoList, HttpStatus.OK).getBody().size());
	}
	
	@Test
	public void fetchTestInfoTest() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("baseline1","value1");
		Baseline baseline = new Baseline(1l,"baseline1","value1",null);
		List<Baseline> baselineList= new ArrayList<Baseline>();
		baselineList.add(baseline);
		Facility facility = new Facility(4l, "this",baselineList );
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		TestResultDto testInfo = new TestResultDto(0, "this",121L,"PAT001", "John Doe",map);
		when(testResultService.fetchTestInfo(121l)).thenReturn(testInfo);
		assertEquals(new ResponseEntity<>(testInfo,HttpStatus.OK),testResultController.fetchTestInfo(121l) );
	}
	
}
