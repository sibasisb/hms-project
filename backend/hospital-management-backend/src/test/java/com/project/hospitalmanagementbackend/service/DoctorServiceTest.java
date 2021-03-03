package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.DoctorInfo;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;

@SpringBootTest
public class DoctorServiceTest {
	
	@InjectMocks
	DoctorService doctorService;
	
	@Mock
	DoctorRepository doctorRepository;
	
	@Test
	void testGetAllDoctors() {
		
		User user = new User();
		Doctor doctor = new Doctor("","","",1,"","",null,new ArrayList<>(),user);
		DoctorInfo doctorInfo = new DoctorInfo();
		List<DoctorInfo> doctorInfoList = new ArrayList<>();
		List<Doctor> doctorList =new ArrayList<>();
		doctorList.add(doctor);
		
		doctorInfo.setDoctorId(doctor.getDoctorId());
		doctorInfo.setName(doctor.getUser().getFirstName()+" "+doctor.getUser().getLastName());
		doctorInfo.setExperience(doctor.getExperience());
		doctorInfo.setQualification(doctor.getQualification());
		doctorInfo.setCharge(doctor.getCharge());
		doctorInfo.setSpeciality(doctor.getSpeciality());
		doctor.getHospital().forEach((hospital->{
			doctorInfo.getHospitalNames().add(hospital.getName());
		}));
		doctorInfoList.add(doctorInfo);
		when(doctorRepository.findAll()).thenReturn(doctorList);
		assertEquals(doctorInfoList.size(), doctorService.getAllDoctors().size());
	}

}
