package com.project.hospitalmanagementbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

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
public class BillingInfo {

	private String patientId;
	private String patientName;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private BigDecimal roomCharges;
	private String facilityName;
	private String doctorName;
	private BigDecimal facilityCharge;
	private BigDecimal doctorCharge;

}
