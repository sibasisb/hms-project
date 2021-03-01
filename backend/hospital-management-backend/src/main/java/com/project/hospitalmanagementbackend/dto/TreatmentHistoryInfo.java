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
public class TreatmentHistoryInfo {

	long treatmentId;
	String patientName;
	int age;
	String gender;
	String doctorName;
	String doctorSpecilaity;
	String prescription;
}
