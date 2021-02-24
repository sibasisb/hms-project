package com.project.hospitalmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
	
	@Query("select p from Appointment a inner join Patient p where a.id=p.id and a.approved=true and a.paid=false")
	public List<Patient> patientsWithFacilityRequests();

}
