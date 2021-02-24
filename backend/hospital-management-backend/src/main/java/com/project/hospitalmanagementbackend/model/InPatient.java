package com.project.hospitalmanagementbackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	/*@OneToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;*/
	
	@Column(name="admission_date")
	private LocalDateTime admissionDate;
	
	@Column(name="discharge_date")
	private LocalDateTime dischargeDate;
	
	@Column(name="room_charges")
	private BigDecimal roomCharges;
	
	@Column(name="paid")
	private boolean paid;
	
	

}
