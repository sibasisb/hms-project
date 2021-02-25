package com.project.hospitalmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	public List<Appointment> findAllByPatient(Patient patient);
	
	@Query("SELECT a FROM Appointment a JOIN FETCH a.patient p WHERE p.patientId = ?1")
	public List<Appointment> getAllAppointmentsByPatient(String patientId);
}
