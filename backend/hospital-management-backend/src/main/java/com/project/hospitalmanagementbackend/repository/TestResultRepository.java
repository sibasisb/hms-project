package com.project.hospitalmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.TestResult;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

	@Query("select t from TestResult t where t.patient.patientId=:patientId and t.appointment.appointmentId=:appointmentId")
	public List<TestResult> getTestResults(@Param("patientId") String patientId,@Param("appointmentId") Long appointmentId);
	
}
