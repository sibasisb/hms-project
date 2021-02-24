package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
