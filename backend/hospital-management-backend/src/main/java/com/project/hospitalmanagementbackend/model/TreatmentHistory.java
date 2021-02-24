package com.project.hospitalmanagementbackend.model;

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
@Table(name="treatment_history")
public class TreatmentHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="treatment_id")
	private long treatmentId;
	
	@Column(name="prescription")
	private String prescription;
	
	@ManyToOne
	@JoinColumn(name="treatment_patient_id")
	private Patient patient;
	
	/*
	 * @ManyToOne
	 * @JoinColumn(name="treatment_doctor_id") 
	 * private Doctor doctor;
	 */
	
}
