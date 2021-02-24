package com.project.hospitalmanagementbackend;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.repository.PatientRepository;

@SpringBootApplication
public class HospitalManagementBackendApplication {

	@Autowired
	private PatientRepository patientRepo;
	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(HospitalManagementBackendApplication.class, args);
		HospitalManagementBackendApplication obj=ctx.getBean(HospitalManagementBackendApplication.class);
		obj.amc();
	}
	@Transactional
	public void amc() {
		Patient pat=new Patient();
		Patient pat2=new Patient();
		Patient pat3=new Patient();
		patientRepo.save(pat);
		patientRepo.save(pat2);
		patientRepo.save(pat3);
	}

}
