package com.project.hospitalmanagementbackend.controller;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.dto.TieUpInfo;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.TieUp;
import com.project.hospitalmanagementbackend.service.TieUpService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TieUpControllerTest {
	
	@InjectMocks
	TieUpController tieUpController;
	
	@Mock
	TieUpService tieUpService;

	@Test
	void getAllTieUpsTest()
	{
		TieUpInfo tieUpInfo=new TieUpInfo(1,"HOSO995","Fortis","HOS0996","Apollo");
		Set<TieUpInfo> infoList=new HashSet<TieUpInfo>();
		infoList.add(tieUpInfo);
		when(tieUpService.getTieUps()).thenReturn(infoList);
		assertEquals(new ResponseEntity<>(infoList, HttpStatus.OK),tieUpController.viewTieUps());
	}
	
	@Test
	void setNewTieUpTest()
	{
		Hospital hospital1 = new Hospital("HOS0995","name1","address","phone","website",null,null);
		Hospital hospital2 = new Hospital("HOS0996","name2","address","phone","website",null,null);
		TieUp tieUp=new TieUp(1l,hospital1,hospital2);
		String success="New Tie Up has been set";
		when(tieUpService.newTieUp(tieUp)).thenReturn(success);
		assertEquals(new ResponseEntity<>(success, HttpStatus.CREATED),tieUpController.setNewTieUps(tieUp));
	}
	
	@Test
	void checkTieUpTest()
	{
		Hospital hospital1 = new Hospital("HOS0995","name1","address","phone","website",null,null);
		Hospital hospital2 = new Hospital("HOS0996","name2","address","phone","website",null,null);
		TieUp tieUp=new TieUp(1l,hospital1,hospital2);
		boolean tieUpFound=true;
		when(tieUpService.checkTieUp(tieUp)).thenReturn(tieUpFound);
		assertEquals(new ResponseEntity<>(tieUpFound, HttpStatus.OK),tieUpController.CheckTieUp(tieUp));
	}
	
	@Test
	void viewTieUpsOfHospital()
	{
		TieUpInfo tieUpInfo=new TieUpInfo(1,"HOSO995","Fortis","HOS0996","Apollo");
		Set<TieUpInfo> infoList=new HashSet<TieUpInfo>();
		infoList.add(tieUpInfo);
		when(tieUpService.getTieUpsOfHospital("HOS0995")).thenReturn(infoList);
		assertEquals(new ResponseEntity<>(infoList, HttpStatus.OK),tieUpController.viewTieUpsOfHospital("HOS0995"));
		
	}
}
