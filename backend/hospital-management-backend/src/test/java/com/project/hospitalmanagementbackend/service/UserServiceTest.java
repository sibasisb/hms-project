package com.project.hospitalmanagementbackend.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.AuthRequestUser;
import com.project.hospitalmanagementbackend.dto.AuthResponseUser;
import com.project.hospitalmanagementbackend.dto.UserInfo;
import com.project.hospitalmanagementbackend.exception.InvalidUserException;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalAdmin;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.SystemAdmin;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;
import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;
import com.project.hospitalmanagementbackend.repository.SystemAdminRepository;
import com.project.hospitalmanagementbackend.repository.UserRepository;
import com.project.hospitalmanagementbackend.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	SystemAdminRepository sysadminRepository;

	@Mock
	PatientRepository patientRepository;

	@Mock
	UserRepository userRepository;
	
	@Mock
	DoctorRepository doctorRepository;

	@Mock
	HospitalRepository hospitalRepository;
	@Test
	void loginAsAdminTestSuccess() {
		SystemAdmin systemAdmin = new SystemAdmin("admin", "admin");
		when(sysadminRepository.findById("admin")).thenReturn(Optional.of(systemAdmin));
		String token = "jwttoken";
		assertEquals(token,userService.getAdmin(systemAdmin));
	}

	@Test
	void loginAsAdminTestFailure() {
		SystemAdmin systemAdmin = new SystemAdmin("admin", "admin");
		SystemAdmin notsystemAdmin = new SystemAdmin("admin1", "admin1");
		when(sysadminRepository.findById("admin")).thenReturn(Optional.of(systemAdmin));
		String token = "jwttoken";
		assertThrows(InvalidUserException.class, () -> userService.getAdmin(notsystemAdmin));
	}

	@Test
	void loginAsUserTestSuccess() {
		AuthRequestUser user = new AuthRequestUser("PAT000001", "pass12$");
		User userInfo = new User(1l, "John", null, null, null, null, null, null, "patient");
		Patient patient = new Patient("PAT000001", userInfo);
		when(patientRepository.findById("PAT000001")).thenReturn(Optional.of(patient));
		when(userRepository.findById(1l)).thenReturn(Optional.of(userInfo));
		AuthResponseUser authResponseUser = new AuthResponseUser("John", "patient", "jwttoken");
		AuthResponseUser responseUser = userService.getUser(user);
		assertEquals(authResponseUser.getToken(),responseUser.getToken());
	}

	@Test
	void loginAsUserTestFailure() {
		AuthRequestUser user = new AuthRequestUser("PAT000001", "pass12$");
		AuthRequestUser notAnUser = new AuthRequestUser("user123", "pwd12$");
		User userInfo = new User(1l, "John", null, null, null, null, null, null, "patient");
		Patient patient = new Patient("PAT000001", userInfo);
		AuthResponseUser authResponseUser = new AuthResponseUser("John", "patient", "jwttoken");
		when(patientRepository.findById("PAT000001")).thenReturn(Optional.of(patient));
		when(userRepository.findById(1l)).thenReturn(Optional.of(userInfo));
		assertThrows(InvalidUserException.class, () -> userService.getUser(notAnUser));
	}
	
	@Test
	void registerTest()
	{
		UserInfo userInfo=new UserInfo();
    	User user = new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "doctor");
    	Hospital hospital=new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
    	Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "05:00PM-07:00PM", new BigDecimal(250.00), new ArrayList<>() ,null);
    	userInfo.setUser(user);
    	userInfo.setDoctor(doctor);
    	userInfo.setHospital(hospital);
    	when(doctorRepository.save(doctor)).thenReturn(doctor);
    	when(hospitalRepository.findById("HOS001")).thenReturn(Optional.of(hospital));
    	String success="User Registered Successfully";
    	assertEquals(success,userService.register(userInfo));
	}
}
