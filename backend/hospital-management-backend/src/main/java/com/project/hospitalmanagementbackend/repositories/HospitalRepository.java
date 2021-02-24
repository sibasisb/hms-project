package com.project.hospitalmanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.Hospital;


public interface HospitalRepository extends JpaRepository<Hospital, String> {

}
