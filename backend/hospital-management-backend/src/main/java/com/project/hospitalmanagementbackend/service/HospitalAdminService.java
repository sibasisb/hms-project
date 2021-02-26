package com.project.hospitalmanagementbackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;

@Service
public class HospitalAdminService {

	@Autowired
	private HospitalAdminRepository hospitalAdminRepository;
	
	@Transactional
	public String getHospitalIdByAdminId(String hospitalAdminId) {
		return hospitalAdminRepository.getHospitalIdByAdminId(hospitalAdminId);
	}
}
