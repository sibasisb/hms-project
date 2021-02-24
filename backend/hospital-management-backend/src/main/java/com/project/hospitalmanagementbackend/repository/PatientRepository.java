package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
