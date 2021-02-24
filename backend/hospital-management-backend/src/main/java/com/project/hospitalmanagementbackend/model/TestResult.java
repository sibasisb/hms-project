package com.project.hospitalmanagementbackend.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="test_result")
public class TestResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="result_id")
	private long resultId;
	
	@Column(name="test_name")
	private String testName;
	
	@ManyToOne
	@JoinColumn(name="result_patient_id")
	private Patient patient;
	
	/*
	 * @OneToOne 
	 * @JoinColumn(name="result_appointment_id") 
	 * private Appointment appointment;
	 */
	
	@Column(name="results")
	private HashMap<String,String> results;
	
}
