package com.project.hospitalmanagementbackend.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.project.hospitalmanagementbackend.model.SystemAdmin;
import com.project.hospitalmanagementbackend.repository.SystemAdminRepository;
import com.project.hospitalmanagementbackend.repository.UserRepository;
import com.project.hospitalmanagementbackend.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	UserService userService;
	
	@Mock
	SystemAdminRepository sysadminReposiory;
	
	@Test
	void loginAsAdminTestSuccess()
	{
		SystemAdmin systemAdmin = new SystemAdmin("admin","admin");
		when(sysadminReposiory.findById("admin")).thenReturn(Optional.of(systemAdmin));
		String token="jwttoken";
		assertEquals(userService.getAdmin(systemAdmin),token);
	}
}
