package com.project.hospitalmanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.HospitalAdmin;


public interface HospitalAdminRepository extends JpaRepository<HospitalAdmin, Long> {

}
