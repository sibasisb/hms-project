package com.project.hospitalmanagementbackend.dto;

import java.time.LocalDate;
import java.util.List;

import com.project.hospitalmanagementbackend.model.TestResultsInformation;

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
public class TestResultPatientInfo {

	private long resultId;
	private String testName;
	private String patientId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	private String contact;
	private List<TestResultsInformation> infos;

}
