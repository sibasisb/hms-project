package com.project.hospitalmanagementbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.model.ReviewQuestionnaire;
import com.project.hospitalmanagementbackend.repository.ReviewQuestionnaireRepository;

@Service
public class ReviewQuestionnaireService {

	@Autowired
	private ReviewQuestionnaireRepository reviewQuestionnaireRepository;
	
	@Transactional
	public String addQuestionService(ReviewQuestionnaire question) {
		ReviewQuestionnaire res=reviewQuestionnaireRepository.save(question);
		if(res==null) 
			return "Question could not be added";
		return "Added question successfully";
	}
	
	@Transactional
	public List<ReviewQuestionnaire> getAllQuestionsService() {
		List<ReviewQuestionnaire> questionList=reviewQuestionnaireRepository.findAll();
		return questionList;
	}
	
}
