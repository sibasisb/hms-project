package com.project.hospitalmanagementbackend.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.TieUpInfo;
import com.project.hospitalmanagementbackend.exception.HospitalNotFoundException;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.TieUp;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.TieUpRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TieUpServiceTest {
	
	@InjectMocks
	TieUpService tieUpService;
	
	@Mock
	TieUpRepository tieUpRepository;
	
	@Mock
	HospitalRepository hospitalRepository;
	
	@Test
	void viewTieUpsTest()
	{
		Hospital hospital1 = new Hospital("HOS0995","name1","address","phone","website",null,null);
		Hospital hospital2 = new Hospital("HOS0996","name2","address","phone","website",null,null);
		TieUp tieUp=new TieUp(1l,hospital1,hospital2);
		List<TieUp> tieUpList=new ArrayList<TieUp>();
		tieUpList.add(tieUp);
		when(tieUpRepository.findAll()).thenReturn(tieUpList);
		TieUpInfo tieUpInfo=new TieUpInfo(1,"HOSO995","name1","HOS0996","name2");
		Set<TieUpInfo> infoList=new HashSet<TieUpInfo>();
		infoList.add(tieUpInfo);
		assertEquals(infoList.size(), tieUpService.getTieUps().size());
	}
	
	@Test
	void newTieUpTest1()
	{
		Hospital hospital1 = new Hospital("HOS0995","name1","address","phone","website",null,null);
		Hospital hospital2 = new Hospital("HOS0996","name2","address","phone","website",null,null);
		Hospital hospital3 = new Hospital("HOS0997","name3","address","phone","website",null,null);
		TieUp tieUpNew=new TieUp(1l,hospital1,hospital3);
		TieUp tieUpOld=new TieUp(1l,hospital1,hospital2);
		when(tieUpRepository.findById(1l)).thenReturn(Optional.of(tieUpOld));
		when(hospitalRepository.findById("HOS0995")).thenReturn(Optional.of(hospital1));
		when(hospitalRepository.findById("HOS0997")).thenReturn(Optional.of(hospital3));
		when(tieUpRepository.save(tieUpOld)).thenReturn(tieUpNew);
		String success="New Tie Up has been set";
		assertEquals(success,tieUpService.newTieUp(tieUpNew));
		
	}
	
	@Test
	void newTieUpTes2()
	{
		Hospital hospital1 = new Hospital("HOS0995","name1","address","phone","website",null,null);
		Hospital hospital2 = new Hospital("HOS0996","name2","address","phone","website",null,null);
		TieUp tieUp=new TieUp(0l,hospital1,hospital2);
		when(tieUpRepository.save(tieUp)).thenReturn(tieUp);
		String success="New Tie Up has been set";
		assertEquals(success,tieUpService.newTieUp(tieUp));
		
	}
	
	@Test
	void checkTieUpTest()
	{
		Hospital hospital1 = new Hospital("HOS0995","name1","address","phone","website",null,null);
		Hospital hospital2 = new Hospital("HOS0996","name2","address","phone","website",null,null);
		TieUp tieUp=new TieUp(1l,hospital1,hospital2);
		when(tieUpRepository.checkTieUp("HOS0995", "HOS0996")).thenReturn(tieUp);
		when(tieUpRepository.checkTieUp("HOS0996", "HOS0995")).thenReturn(null);
		boolean tieUpFound=true;
		assertEquals(tieUpFound,tieUpService.checkTieUp(tieUp));
	}
	
	@Test
	void getTieUpsOfHospitalSuccessTest()
	{
		Hospital hospital1 = new Hospital("HOS0995","name1","address","phone","website",null,null);
		Hospital hospital2 = new Hospital("HOS0996","name2","address","phone","website",null,null);
		TieUp tieUp=new TieUp(1l,hospital1,hospital2);
		Set<TieUp> tieUpList=new HashSet<TieUp>();
		tieUpList.add(tieUp);
		when(hospitalRepository.findById("HOS0995")).thenReturn(Optional.of(hospital1));
		when(tieUpRepository.getTieUpOfHospital("HOS0995")).thenReturn(tieUpList);
		TieUpInfo tieUpInfo=new TieUpInfo(1,null,null,"HOS0996","name2");
		Set<TieUpInfo> infoList=new HashSet<TieUpInfo>();
		infoList.add(tieUpInfo);
		assertEquals(infoList.size(), tieUpService.getTieUpsOfHospital("HOS0995").size());
	}
	
	@Test
	void getTieUpsOfHospitalFailureTest()
	{
		Hospital hospital1 = new Hospital("HOS0995","name1","address","phone","website",null,null);
		Hospital hospital2 = new Hospital("HOS0996","name2","address","phone","website",null,null);
		TieUp tieUp=new TieUp(1l,hospital1,hospital2);
		Set<TieUp> tieUpList=new HashSet<TieUp>();
		tieUpList.add(tieUp);
		when(hospitalRepository.findById("HOS0995")).thenReturn(Optional.of(hospital1));
		when(tieUpRepository.getTieUpOfHospital("HOS0995")).thenReturn(tieUpList);
		TieUpInfo tieUpInfo=new TieUpInfo(1,null,null,"HOS0996","name2");
		Set<TieUpInfo> infoList=new HashSet<TieUpInfo>();
		infoList.add(tieUpInfo);
		assertThrows(HospitalNotFoundException.class,()-> tieUpService.getTieUpsOfHospital("HOS0998"));
	}

}
