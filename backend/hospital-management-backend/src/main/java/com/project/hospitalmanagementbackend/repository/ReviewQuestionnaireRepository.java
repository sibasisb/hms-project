package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hospitalmanagementbackend.model.ReviewQuestionnaire;

@Repository
public interface ReviewQuestionnaireRepository extends JpaRepository<ReviewQuestionnaire, Long> {

}
