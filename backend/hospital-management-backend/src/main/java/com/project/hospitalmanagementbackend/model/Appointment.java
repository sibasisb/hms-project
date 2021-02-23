package com.project.hospitalmanagementbackend.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Appointment {

	@Id
	private Long appointmentId;

	// TODO: add missing model associations
	// private Patient patient

	// private Doctor doctor

	// private Hospital hospital

	// private HospitalFacility f

	private LocalDate appointmentDate;

	private LocalTime appointmentTime;

	private String remarks;

	private byte[] medicalHistory;
}
