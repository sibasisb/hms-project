package com.project.hospitalmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.InPatient;

@Repository
public interface InPatientRepository extends JpaRepository<InPatient, Long> {
	
	@Query("select ip from InPatient ip where ip.hospital.hospitalId= ?1")
	public List<InPatient> findInPatientByHospital(String hospitalId);
	
	@Query("select a from InPatient a where a.paid=false and a.hospital.hospitalId= ?1 and a.patient.patientId = ?2")
	public List<InPatient> findUnpaidInpatients(String hospitalId,String patientId);
	
	@Modifying
	@Query(" update InPatient a set a.paid=true where a.paid=false and a.hospital.hospitalId= ?1 and a.patient.patientId = ?2")
	public void payForInpatients(String hospitalId,String patientId);

}
