package com.project.hospitalmanagementbackend.dto;

import java.util.HashMap;

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
public class TestResultDto {

	private long resultId;
	private String testName;
	private String patientId;
	private String patientName;
	private HashMap<String,String> infos;
	
}
