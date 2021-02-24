package com.project.hospitalmanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, String> {

}
