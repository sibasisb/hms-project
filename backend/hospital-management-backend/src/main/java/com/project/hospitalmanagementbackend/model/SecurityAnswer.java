package com.project.hospitalmanagementbackend.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "security_answers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SecurityAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_id")
	private Long answerId;
	
	@Column(name = "answer")
	private String answer;
	
	@OneToOne
	@JoinColumn(name="question_id")
	private SecurityQuestions question;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
