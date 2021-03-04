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
public class TieUpInfo {

	long tieUpId;
	String hospital1Id;
	String hospital1Name;
	String hospital2Id;
	String hospital2Name;
	
}
