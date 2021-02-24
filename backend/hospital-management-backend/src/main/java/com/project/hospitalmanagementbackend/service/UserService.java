package com.project.hospitalmanagementbackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.SystemAdmin;
import com.project.hospitalmanagementbackend.repository.SystemAdminRepository;



@Service
public class UserService {

	@Autowired
	SystemAdminRepository sysAdminRepository;
	public String getAdmin(SystemAdmin admin) {
		// TODO Auto-generated method stub
		Optional<SystemAdmin> adminFound = sysAdminRepository.findById(admin.getUserId());
		if(adminFound.isPresent())
			return "jwttoken";
		else
			throw new RuntimeException("Invalid/Username Password");
	}

}
