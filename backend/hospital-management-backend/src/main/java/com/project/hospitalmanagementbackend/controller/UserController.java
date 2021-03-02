package com.project.hospitalmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.dto.AuthRequestUser;
import com.project.hospitalmanagementbackend.dto.AuthResponseUser;
import com.project.hospitalmanagementbackend.dto.UserInfo;
import com.project.hospitalmanagementbackend.model.SystemAdmin;
import com.project.hospitalmanagementbackend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/login/admin")
	public ResponseEntity<?> loginAsAdmin(@RequestBody SystemAdmin sysadmin) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(userService.getAdmin(sysadmin),HttpStatus.OK);
	}

	@PostMapping("/login/user")
	public ResponseEntity<AuthResponseUser> loginAsUser(@RequestBody AuthRequestUser user) {
		// TODO Auto-generated method stub
		return new ResponseEntity<AuthResponseUser>(userService.getUser(user),HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserInfo userInfo) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(userService.register(userInfo), HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("Authorization")  String header)
	{
		return new ResponseEntity<>(userService.logout(header),HttpStatus.OK);
	}

}
