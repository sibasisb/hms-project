package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.TestResult;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

}
