package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.dto.FeedbackDto;
import com.project.hospitalmanagementbackend.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addFeedbackController(@RequestBody List<FeedbackDto> feedbackDtoList){
		String res=feedbackService.addFeedbackService(feedbackDtoList);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
