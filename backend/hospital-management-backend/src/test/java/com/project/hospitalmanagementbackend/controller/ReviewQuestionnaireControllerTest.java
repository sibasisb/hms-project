package com.project.hospitalmanagementbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.model.ReviewQuestionnaire;
import com.project.hospitalmanagementbackend.service.ReviewQuestionnaireService;

@SpringBootTest
public class ReviewQuestionnaireControllerTest {

	@InjectMocks
	private ReviewQuestionnaireController reviewQuestionnaireController;
	
	@Mock
	private ReviewQuestionnaireService reviewQuestionnaireService;
	
	@Test
	public void testAddQuestionController() {
		String res="Added question successfully";
		ReviewQuestionnaire question=new ReviewQuestionnaire(1,"How was the doctor?");
		when(reviewQuestionnaireService.addQuestionService(question)).thenReturn(res);
		assertEquals(new ResponseEntity<>(res,HttpStatus.OK),reviewQuestionnaireController.addQuestionController(question));
	}
	
	@Test
	public void testGetAllQuestionsController() {
		ReviewQuestionnaire question=new ReviewQuestionnaire(1,"How was the doctor?");
		List<ReviewQuestionnaire> questionList=new ArrayList<ReviewQuestionnaire>();
		questionList.add(question);
		when(reviewQuestionnaireService.getAllQuestionsService()).thenReturn(questionList);
		assertEquals(new ResponseEntity<>(questionList, HttpStatus.OK), reviewQuestionnaireController.getAllQuestionsController());
	}
	
}
