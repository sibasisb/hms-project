package com.project.hospitalmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.TreatmentHistory;

@Repository
public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, Long> {

	List<TreatmentHistory> findByPatient_PatientId(String patientId);

	TreatmentHistory findByPatient_PatientIdAndDoctor_DoctorId(String patienId, String doctorId);

}
