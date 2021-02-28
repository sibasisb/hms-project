package com.project.hospitalmanagementbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.FeedbackDto;
import com.project.hospitalmanagementbackend.exception.AppointmentNotFoundException;
import com.project.hospitalmanagementbackend.exception.QuestionNotFoundException;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Feedback;
import com.project.hospitalmanagementbackend.model.ReviewQuestionnaire;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.FeedbackRepository;
import com.project.hospitalmanagementbackend.repository.ReviewQuestionnaireRepository;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private ReviewQuestionnaireRepository reviewQuestionnaireRepository;
	
	@Transactional
	public String addFeedbackService(List<FeedbackDto> feedbackDtoList) {
		feedbackDtoList.forEach((feedbackDto)->{
			Optional<ReviewQuestionnaire> q=reviewQuestionnaireRepository.findById(feedbackDto.getQuestionId());
			if(!q.isPresent()) {
				throw new QuestionNotFoundException("Question id not valid");
			}
			Optional<Appointment> a=appointmentRepository.findById(feedbackDto.getAppointmentId());
			if(!a.isPresent()) {
				throw new AppointmentNotFoundException("Appointment not found");
			}
			Feedback feedback=new Feedback();
			feedback.setAnswers(feedbackDto.getAnswers());
			feedback.setAppointment(a.get());
			feedback.setReviewQuestionnaire(q.get());
			feedbackRepository.save(feedback);
		});
		return "Feedback has been added";
	}
}
