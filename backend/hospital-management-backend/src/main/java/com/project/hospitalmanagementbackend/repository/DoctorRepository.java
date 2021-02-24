package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, String> {

}
