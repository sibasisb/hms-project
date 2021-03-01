package com.project.hospitalmanagementbackend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "in_patient_details")
public class InPatient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="in_patient_id")
	private long inPatientId;
	
	@OneToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;
	
	@Column(name="admission_date")
	private LocalDate admissionDate;
	
	@Column(name="admission_time")
	private LocalTime admissionTime;
	
	@Column(name="discharge_date")
	private LocalDate dischargeDate;
	
	@Column(name="discharge_time")
	private LocalTime dischargeTime;
	
	@Column(name="room_charges")
	private BigDecimal roomCharges;
	
	@Column(name="paid")
	private boolean paid;
	
	

}
