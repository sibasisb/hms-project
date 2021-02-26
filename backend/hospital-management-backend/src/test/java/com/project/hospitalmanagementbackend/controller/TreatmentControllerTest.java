package com.project.hospitalmanagementbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.dto.TreatmentHistoryInfo;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.TreatmentHistory;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.service.TreatmentService;

@SpringBootTest
public class TreatmentControllerTest {

	@InjectMocks
	TreatmentController treatmentController;
	
	@Mock
	TreatmentService treatmentService;
	
	@Test
	void updateTreatmentHistoryTest()
	{
		
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		TreatmentHistory treatmentHistory = new TreatmentHistory(1l,"Mild fever and cough.Take Calpol",null,null);
		String success="Treatment history updated successfully";
		when(treatmentService.updateTreatmentHistory("PAT001","DOC001",treatmentHistory)).thenReturn(success);
		assertEquals(new ResponseEntity<>(success, HttpStatus.CREATED),treatmentController.updateTreatmentHistory("PAT001","DOC001",treatmentHistory));
	}
	
	
	@Test
	void getAllTreatmentHistoryTest()
	{
	
		TreatmentHistoryInfo treatmentHistoryInfo = new TreatmentHistoryInfo("John",18,"male","Munna Bhai","cardio","Mild fever.Take calpol");
		ArrayList<TreatmentHistoryInfo> historyList = new ArrayList<TreatmentHistoryInfo>();
		historyList.add(treatmentHistoryInfo);
		when(treatmentService.getAllTreatmentHistory("PAT001")).thenReturn(historyList);
		assertEquals(new ResponseEntity<>(historyList, HttpStatus.OK),treatmentController.getAllTreatmentHistory("PAT001"));
		
		
	}
	
	@Test
	void getTreatmentHistoryTest()
	{
	
		TreatmentHistoryInfo treatmentHistoryInfo = new TreatmentHistoryInfo("John",18,"male","Munna Bhai","cardio","Mild fever.Take calpol");
		when(treatmentService.getTreatmentHistory("PAT001","DOC001")).thenReturn(treatmentHistoryInfo);
		assertEquals(new ResponseEntity<>(treatmentHistoryInfo, HttpStatus.OK),treatmentController.getTreatmentHistory("PAT001","DOC001"));
		
		
	}
	
}
