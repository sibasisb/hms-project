package com.project.hospitalmanagementbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

}
