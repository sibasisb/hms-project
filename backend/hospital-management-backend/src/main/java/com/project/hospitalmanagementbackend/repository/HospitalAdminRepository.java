package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.HospitalAdmin;


public interface HospitalAdminRepository extends JpaRepository<HospitalAdmin, String> {

}
