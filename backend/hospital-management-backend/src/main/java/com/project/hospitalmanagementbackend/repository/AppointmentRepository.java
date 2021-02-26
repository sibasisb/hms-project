package com.project.hospitalmanagementbackend.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	public List<Appointment> findByPatient_PatientId(String patientId);

//	@Query("SELECT a FROM Appointment a left join fetch a.patient left join fetch a.doctor left join fetch a.hospitalFacility left join fetch a.hospital WHERE a.patient.patientId = ?1")
	@Query("Select a from Appointment a where a.patient.patientId=?1")
	public Set<Appointment> getAllAppointmentsByPatient(String patientId);
	
	@Query("from Appointment a where a.approved=true and a.paid=false and a.hospitalFacility.hospitalFacilityId is not null and a.hospital.hospitalId=:hospitalId")
	public List<Appointment> findPatientsWithFacilityRequests(@Param("hospitalId") String hospitalId);
	
	@Query("SELECT a from Appointment a where a.doctor.doctorId=?1")
	public List<Appointment> findAppointmentsByDoctorId(String doctorId);
	
}
