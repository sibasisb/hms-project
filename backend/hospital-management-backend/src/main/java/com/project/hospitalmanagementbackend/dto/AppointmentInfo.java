package com.project.hospitalmanagementbackend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class AppointmentInfo {
	Long appointmentId;
	LocalDate appointmentDate;
	LocalTime appointmentTime;
	String patientId;
	String patientName;
	String doctorName;
	String facilityName;
	String hospitalName;
	String remarks;
	byte[] medicalRecords;
	boolean approved;
	boolean paid;
}
