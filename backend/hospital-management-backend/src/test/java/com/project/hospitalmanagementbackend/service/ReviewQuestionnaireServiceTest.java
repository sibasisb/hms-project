package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.model.ReviewQuestionnaire;
import com.project.hospitalmanagementbackend.repository.ReviewQuestionnaireRepository;

@SpringBootTest
public class ReviewQuestionnaireServiceTest {

	@InjectMocks
	private ReviewQuestionnaireService reviewQuestionnaireService;
	
	@Mock
	private ReviewQuestionnaireRepository reviewQuestionnaireRepository;
	
	@Test
	public void testAddQuestionServiceFailure() {
		String res="Question could not be added";
		ReviewQuestionnaire question=new ReviewQuestionnaire(1,"How was the doctor?");
		when(reviewQuestionnaireRepository.save(question)).thenReturn(null);
		assertEquals(res,reviewQuestionnaireService.addQuestionService(question));
	}
	
	@Test
	public void testAddQuestionService() {
		String res="Added question successfully";
		ReviewQuestionnaire question=new ReviewQuestionnaire(1,"How was the doctor?");
		when(reviewQuestionnaireRepository.save(question)).thenReturn(question);
		assertEquals(res,reviewQuestionnaireService.addQuestionService(question));
	}
	
	@Test
	public void testGetAllQuestionsService() {
		ReviewQuestionnaire question=new ReviewQuestionnaire(1,"How was the doctor?");
		List<ReviewQuestionnaire> questionList=new ArrayList<ReviewQuestionnaire>();
		questionList.add(question);
		when(reviewQuestionnaireRepository.findAll()).thenReturn(questionList);
		assertEquals(reviewQuestionnaireService.getAllQuestionsService(),questionList);
	}
	
}
