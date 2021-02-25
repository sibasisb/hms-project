package com.project.hospitalmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("select a from Appointment a where a.approved=true and a.paid=false")
	public List<Appointment> findPatientsWithFacilityRequests();
	
}
