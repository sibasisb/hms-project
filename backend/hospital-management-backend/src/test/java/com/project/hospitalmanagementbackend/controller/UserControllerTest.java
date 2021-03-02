package com.project.hospitalmanagementbackend.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.dto.AuthRequestUser;
import com.project.hospitalmanagementbackend.dto.AuthResponseUser;
import com.project.hospitalmanagementbackend.dto.UserInfo;
import com.project.hospitalmanagementbackend.exception.InvalidUserException;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.SystemAdmin;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	private UserService userService;

	@Test
	void loginAsAdminTest() {
		SystemAdmin admin = new SystemAdmin("admin", "admin");
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDYxNDEyMywiaWF0IjoxNjE0NTc4MTIzfQ.6tzNDzRdOBc8X1Ya8GG7wJAam_zK0R8-Lmt9oDcPNow";
		AuthResponseUser authResponseUser = new AuthResponseUser("admin", null, "admin", token);
		when(userService.getAdmin(admin)).thenReturn(authResponseUser);

		assertEquals(userController.loginAsAdmin(admin), new ResponseEntity<>(authResponseUser, HttpStatus.OK));
	}

	@Test
	void loginAsUserTest() {
		AuthRequestUser user = new AuthRequestUser("PAT000002", "pass12$");
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQQVQwMDAwMDIiLCJleHAiOjE2MTQ2MTM4NzAsImlhdCI6MTYxNDU3Nzg3MH0.42c83tL2mQ5Ose_nfZ-OFEppj0A6slrUeSn-LQnzoL4";
		AuthResponseUser authResponseUser = new AuthResponseUser("PAT000001", "John", "patient", token);
		when(userService.getUser(user)).thenReturn(authResponseUser);
		assertEquals(new ResponseEntity<AuthResponseUser>(authResponseUser, HttpStatus.OK),
				userController.loginAsUser(user));
	}

	@Test
	void registerUserTest() {
		UserInfo userInfo = new UserInfo();
		User user = new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs",
				"circiut", "Doctor");
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "05:00PM-07:00PM",
				new BigDecimal(250.00), null, null);
		userInfo.setDoctor(doctor);
		userInfo.setUser(user);
		String success = "User Registered Successfully.Your User ID is DOC001";
		when(userService.register(userInfo)).thenReturn(success);
		assertEquals(new ResponseEntity<>(success, HttpStatus.OK), userController.registerUser(userInfo));

	}

	@Test
	void logoutTest() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQQVQwMDAwMDIiLCJleHAiOjE2MTQ2MTM4NzAsImlhdCI6MTYxNDU3Nzg3MH0.42c83tL2mQ5Ose_nfZ-OFEppj0A6slrUeSn-LQnzoL4";
		String success = "User logged out";
		when(userService.logout(token)).thenReturn(success);
		assertEquals(new ResponseEntity<>(success, HttpStatus.OK), userController.logout(token));
	}
}
