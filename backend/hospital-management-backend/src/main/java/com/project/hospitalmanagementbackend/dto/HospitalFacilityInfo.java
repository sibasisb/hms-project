package com.project.hospitalmanagementbackend.dto;

import java.math.BigDecimal;

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
public class HospitalFacilityInfo {

	private long hospitalFacilityId;
	private String facilityName;
	private String description;
	private String remarks;
	private BigDecimal charges;

}
