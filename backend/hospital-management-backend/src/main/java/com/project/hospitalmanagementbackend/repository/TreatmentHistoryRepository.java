package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.TreatmentHistory;

public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, Long> {

}
