package com.project.hospitalmanagementbackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;


@SpringBootApplication
public class HospitalManagementBackendApplication {

	
	public static void main(String[] args) {

		SpringApplication.run(HospitalManagementBackendApplication.class, args);
		
	}
	

}
