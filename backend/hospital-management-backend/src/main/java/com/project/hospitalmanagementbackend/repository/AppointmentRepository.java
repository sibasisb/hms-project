package com.project.hospitalmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("from Appointment a where a.approved=true and a.paid=false and a.hospital.hospitalId=:hospitalId")
	public List<Appointment> findPatientsWithFacilityRequests(@Param("hospitalId") String hospitalId);
	
}
