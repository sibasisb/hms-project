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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long appointmentId;

	@OneToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@OneToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@OneToOne
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
}
