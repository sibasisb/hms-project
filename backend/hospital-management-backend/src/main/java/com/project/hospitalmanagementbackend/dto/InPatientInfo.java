package com.project.hospitalmanagementbackend.dto;

import java.math.BigDecimal;
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
public class InPatientInfo {

	private long inPatientId;
	private String patientId;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDate admissionDate;
	private LocalTime admissionTime;
	private LocalDate dischargeDate;
	private LocalTime dischargeTime;
	private BigDecimal roomCharges;
	private boolean paid;

}
