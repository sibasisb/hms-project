package com.project.hospitalmanagementbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedbackDto {

	private Integer answers;
	private Long appointmentId;
	private long questionId;
	
}
