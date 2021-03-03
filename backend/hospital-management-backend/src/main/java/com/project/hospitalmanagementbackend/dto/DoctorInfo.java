package com.project.hospitalmanagementbackend.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class DoctorInfo {

	private String doctorId;
	private String name;
	private String qualification;
	private String speciality;
	private int experience;
	private String availableDays;
	private String availableTime;
	private BigDecimal charge;
	private List<String> hospitalNames = new ArrayList<>() ;

}
