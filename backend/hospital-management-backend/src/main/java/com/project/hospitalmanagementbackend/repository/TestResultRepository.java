package com.project.hospitalmanagementbackend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.TestResult;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

	@Query("select t from TestResult t left join fetch t.infos where t.patient.patientId=:patientId and t.appointment.appointmentId=:appointmentId")
	public Set<TestResult> getTestResults(@Param("patientId") String patientId,@Param("appointmentId") Long appointmentId);

	@Query("select t from TestResult t left join fetch t.infos where t.patient.patientId=?1")
	public Set<TestResult> getTestResultsByPatientId(String patientId);
	
	@Query("select t from TestResult t left join fetch t.infos where t.resultId=?1")
	public TestResult findByTestResultId(long testResultId);
	
}
