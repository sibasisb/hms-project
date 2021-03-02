package com.project.hospitalmanagementbackend.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.project.hospitalmanagementbackend.dto.AuthRequestUser;
import com.project.hospitalmanagementbackend.dto.UserInfo;
import com.project.hospitalmanagementbackend.exception.InvalidUserException;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.SystemAdmin;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;
import com.project.hospitalmanagementbackend.repository.SystemAdminRepository;
import com.project.hospitalmanagementbackend.repository.UserRepository;
import com.project.hospitalmanagementbackend.util.BlacklistedTokenHandler;
import com.project.hospitalmanagementbackend.util.JwtUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
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

	@Mock
	AuthenticationManager authenticationManager;

	@Mock
	BlacklistedTokenHandler blacklistedTokenHandler;

	@Mock
	JwtUtil jwtutil;

	@Test
	void authenticateTestSuccuess() {
		AuthRequestUser user = new AuthRequestUser("PAT000002", "pass12$");
		Authentication authToken = new UsernamePasswordAuthenticationToken("PAT000002", "pass12$");
		when(authenticationManager.authenticate(authToken)).thenReturn(authToken);
		String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDYxNDEyMywiaWF0IjoxNjE0NTc4MTIzfQ.6tzNDzRdOBc8X1Ya8GG7wJAam_zK0R8-Lmt9oDcPNow";
		when(jwtutil.generateToken("PAT000002")).thenReturn(expectedToken);
		assertEquals(expectedToken, userService.authenticate(user));
	}

	@Test
	void authenticateTestFailure() {
		AuthRequestUser user = new AuthRequestUser("PA000002", "pass12$");
		Authentication authToken = new UsernamePasswordAuthenticationToken("PA000002", "pass12$");
		when(authenticationManager.authenticate(authToken)).thenThrow(new RuntimeException());
		String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDYxNDEyMywiaWF0IjoxNjE0NTc4MTIzfQ.6tzNDzRdOBc8X1Ya8GG7wJAam_zK0R8-Lmt9oDcPNow";
		when(jwtutil.generateToken("PAT000002")).thenReturn(expectedToken);
		assertThrows(InvalidUserException.class, () -> userService.authenticate(user));
	}

	@Test
	void loadByUsernameTestSuccess() {
		User userInfo = new User(1l, "John", null, null, null, null, null, "pass12$", "patient");
		Patient patient = new Patient("PAT000002", userInfo);
		when(patientRepository.findById("PAT000002")).thenReturn(Optional.of(patient));
		ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("patient"));
		org.springframework.security.core.userdetails.User userFound = new org.springframework.security.core.userdetails.User(
				"PAT000002", "pass12$", list);
		assertEquals(userFound.getPassword(), userService.loadUserByUsername("PAT000002").getPassword());

	}

	@Test
	void loadByUsernameTestFailure() {
		User userInfo = new User(1l, "John", null, null, null, null, null, "pass12$", "patient");
		Patient patient = new Patient("PAT000002", userInfo);
		when(patientRepository.findById("PAT000002")).thenReturn(Optional.of(patient));
		ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("patient"));
		org.springframework.security.core.userdetails.User userFound = new org.springframework.security.core.userdetails.User(
				"PAT000002", "pass12$", list);
		assertThrows(InvalidUserException.class, () -> userService.loadUserByUsername("PAT000001").getPassword());

	}

	@Test
	void loginAsAdminTestSuccess() {
		SystemAdmin systemAdmin = new SystemAdmin("admin", "admin");
		Authentication authToken = new UsernamePasswordAuthenticationToken("admin", "admin");
		when(authenticationManager.authenticate(authToken)).thenReturn(authToken);
		String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDYxNDEyMywiaWF0IjoxNjE0NTc4MTIzfQ.6tzNDzRdOBc8X1Ya8GG7wJAam_zK0R8-Lmt9oDcPNow";
		when(jwtutil.generateToken("admin")).thenReturn(expectedToken);
		doNothing().when(blacklistedTokenHandler).unsetBlacklistToken(expectedToken);
		String actualToken = userService.getAdmin(systemAdmin).getToken();
		assertEquals(expectedToken, actualToken);
	}

	@Test
	void loginAsAdminTestFailure() {
		SystemAdmin notsystemAdmin = new SystemAdmin("admin1", "admin1");
		Authentication authToken = new UsernamePasswordAuthenticationToken("admin1", "admin1");
		when(authenticationManager.authenticate(authToken)).thenThrow(new RuntimeException());
		String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDYxNDEyMywiaWF0IjoxNjE0NTc4MTIzfQ.6tzNDzRdOBc8X1Ya8GG7wJAam_zK0R8-Lmt9oDcPNow";
		when(jwtutil.generateToken("admin")).thenReturn(expectedToken);
		doNothing().when(blacklistedTokenHandler).unsetBlacklistToken(expectedToken);
		assertThrows(InvalidUserException.class, () -> userService.getAdmin(notsystemAdmin));
	}

	@Test
	void loginAsUserTestSuccess() {
		AuthRequestUser user = new AuthRequestUser("PAT000002", "pass12$");
		User userInfo = new User(1l, "John", null, null, null, null, null, null, "patient");
		Patient patient = new Patient("PAT000002", userInfo);
		Authentication authToken = new UsernamePasswordAuthenticationToken("PAT000002", "pass12$");
		when(authenticationManager.authenticate(authToken)).thenReturn(authToken);
		String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDYxNDEyMywiaWF0IjoxNjE0NTc4MTIzfQ.6tzNDzRdOBc8X1Ya8GG7wJAam_zK0R8-Lmt9oDcPNow";
		when(jwtutil.generateToken("PAT000002")).thenReturn(expectedToken);
		doNothing().when(blacklistedTokenHandler).unsetBlacklistToken(expectedToken);
		when(patientRepository.findById("PAT000002")).thenReturn(Optional.of(patient));
		String actualToken = userService.getUser(user).getToken();
		assertEquals(expectedToken, actualToken);
	}

	@Test
	void loginAsUserTestFailure() {
		AuthRequestUser user = new AuthRequestUser("PA000002", "pass12$");
		User userInfo = new User(1l, "John", null, null, null, null, null, null, "patient");
		Patient patient = new Patient("PAT000002", userInfo);
		Authentication authToken = new UsernamePasswordAuthenticationToken("PAT000002", "pass12$");
		when(authenticationManager.authenticate(authToken)).thenReturn(authToken);
		String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDYxNDEyMywiaWF0IjoxNjE0NTc4MTIzfQ.6tzNDzRdOBc8X1Ya8GG7wJAam_zK0R8-Lmt9oDcPNow";
		when(jwtutil.generateToken("PAT000002")).thenReturn(expectedToken);
		doNothing().when(blacklistedTokenHandler).unsetBlacklistToken(expectedToken);
		when(patientRepository.findById("PAT000002")).thenReturn(Optional.of(patient));
		assertThrows(InvalidUserException.class, () -> userService.getUser(user));
	}

	@Test
	void registerTest() {
		UserInfo userInfo = new UserInfo();
		User user = new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs",
				"circiut", "doctor");
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "05:00PM-07:00PM",
				new BigDecimal(250.00), new ArrayList<>(), null);
		userInfo.setUser(user);
		userInfo.setDoctor(doctor);
		userInfo.setHospital(hospital);
		when(doctorRepository.save(doctor)).thenReturn(doctor);
		when(hospitalRepository.findById("HOS001")).thenReturn(Optional.of(hospital));
		String success = "User Registered Successfully.Your User ID is DOC001";
		assertEquals(success, userService.register(userInfo));
	}

	@Test
	void logoutTest() {
		String authorizationHeader = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDYxNDEyMywiaWF0IjoxNjE0NTc4MTIzfQ.6tzNDzRdOBc8X1Ya8GG7wJAam_zK0R8-Lmt9oDcPNow";
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDYxNDEyMywiaWF0IjoxNjE0NTc4MTIzfQ.6tzNDzRdOBc8X1Ya8GG7wJAam_zK0R8-Lmt9oDcPNow";
		doNothing().when(blacklistedTokenHandler).setBlacklistToken(token);
		String success = "User logged out";
		assertEquals(success, userService.logout(authorizationHeader));
	}
}
