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
    void loginAsAdminTest()
    {
    	SystemAdmin admin=new SystemAdmin("admin","admin");
    	String token ="jwttoken";
    	when(userService.getAdmin(admin)).thenReturn(token);
    	
    	assertEquals(userController.loginAsAdmin(admin),new ResponseEntity<>(token, HttpStatus.OK));
    }
   
    @Test
    void loginAsUserTest()
    {
    	AuthRequestUser user = new AuthRequestUser("PAT000001","pass12$"); 
    	AuthResponseUser authResponseUser = new AuthResponseUser("PAT000001","John","patient","jwttoken");
    	when(userService.getUser(user)).thenReturn(authResponseUser);
    	assertEquals(new ResponseEntity<AuthResponseUser>(authResponseUser, HttpStatus.OK),userController.loginAsUser(user));
    }
    
    @Test
    void registerUserTest()
    {
    	UserInfo userInfo=new UserInfo();
    	User user = new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor");
    	Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "05:00PM-07:00PM", new BigDecimal(250.00), null ,null);
    	userInfo.setDoctor(doctor);
    	userInfo.setUser(user);
    	String success="User Registered Successfully.Your User ID is DOC001";
    	when(userService.register(userInfo)).thenReturn(success);
    	assertEquals(new ResponseEntity<>(success, HttpStatus.OK),userController.registerUser(userInfo));
    	
    	
    }
}
