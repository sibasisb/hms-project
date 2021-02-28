package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.ReviewQuestionnaire;
import com.project.hospitalmanagementbackend.service.ReviewQuestionnaireService;

@RestController
@RequestMapping("/reviewquestion")
public class ReviewQuestionnaireController {

	@Autowired
	private ReviewQuestionnaireService reviewQuestionnaireService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addQuestionController(@RequestBody ReviewQuestionnaire question)	{
		String res=reviewQuestionnaireService.addQuestionService(question);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getAllQuestionsController()	{
		List<ReviewQuestionnaire> questionList=reviewQuestionnaireService.getAllQuestionsService();
		return new ResponseEntity<>(questionList,HttpStatus.OK);
	}

}
