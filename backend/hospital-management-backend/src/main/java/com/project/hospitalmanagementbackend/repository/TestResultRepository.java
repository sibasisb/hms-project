package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.TestResult;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {

}
