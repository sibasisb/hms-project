package com.project.hospitalmanagementbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="result_infos")
public class TestResultsInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="result_info_id")
	private Long resultInfoId;
	
	@Column(name="result_info_name")
	private String resultInfoName;
	
	@Column(name="result_info_value")
	private String resultInfoValue;
	
	@ManyToOne
	@JoinColumn(name="test_result_id")
	private TestResult testResult;
	
	
}
