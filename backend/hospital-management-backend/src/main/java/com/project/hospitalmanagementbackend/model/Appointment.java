package com.project.hospitalmanagementbackend.model;

import java.time.LocalDate;
import java.time.LocalTime;

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
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long appointmentId;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	@ManyToOne
	@JoinColumn(name = "hospital_facility_id")
	private HospitalFacility hospitalFacility;

	@Column(name = "appointment_date")
	private LocalDate appointmentDate;

	@Column(name = "appointment_time")
	private LocalTime appointmentTime;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "medical_records")
	private byte[] medicalRecords;

	@Column(name = "approved")
	private Boolean approved;

	@Column(name = "paid")
	private Boolean paid;
}
