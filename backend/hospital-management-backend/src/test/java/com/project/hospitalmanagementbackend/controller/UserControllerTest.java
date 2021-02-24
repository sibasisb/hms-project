package com.project.hospitalmanagementbackend.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.project.hospitalmanagementbackend.model.SystemAdmin;
import com.project.hospitalmanagementbackend.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@SpringBootTest
public class UserControllerTest {

	@InjectMocks
	UserController userController;
	
    @Mock
    private UserService userService;
    
    @Test
    void loginAsAdminTestSuccess()
    {
    	SystemAdmin admin=new SystemAdmin("admin","admin");
    	String token ="jwttoken";
    	when(userService.getAdmin(admin)).thenReturn(token);
    	
    	assertEquals(userController.loginAsAdmin(admin),new ResponseEntity<>(token, HttpStatus.OK));
    }
    
}
