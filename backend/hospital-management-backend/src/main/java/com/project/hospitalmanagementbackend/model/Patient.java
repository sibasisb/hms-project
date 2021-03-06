package com.project.hospitalmanagementbackend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.project.hospitalmanagementbackend.util.StringPrefixedPatientIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="patient")
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @GenericGenerator(
        name = "patient_seq", 
        strategy = "com.project.hospitalmanagementbackend.util.StringPrefixedPatientIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedPatientIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringPrefixedPatientIdGenerator.VALUE_PREFIX_PARAMETER, value = "PAT"),
            @Parameter(name = StringPrefixedPatientIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d") })
	@Column(name="patient_id")
	private String patientId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;

}
