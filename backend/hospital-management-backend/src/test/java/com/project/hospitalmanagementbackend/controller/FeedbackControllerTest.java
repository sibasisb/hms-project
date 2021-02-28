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

import com.project.hospitalmanagementbackend.dto.FeedbackDto;
import com.project.hospitalmanagementbackend.service.FeedbackService;

@SpringBootTest
public class FeedbackControllerTest {

	@InjectMocks
	private FeedbackController feedbackController;
	
	@Mock
	private FeedbackService feedbackService;
	
	@Test
	public void testAddFeedbackController() {
		String res="Feedback has been added";
		List<FeedbackDto> feedbackDtoList=new ArrayList<FeedbackDto>();
		FeedbackDto feedbackDto=new FeedbackDto(5,1L,1L);
		feedbackDtoList.add(feedbackDto);
		when(feedbackService.addFeedbackService(feedbackDtoList)).thenReturn(res);
		assertEquals(new ResponseEntity<>(res,HttpStatus.OK),feedbackController.addFeedbackController(feedbackDtoList));
	}
	
}
