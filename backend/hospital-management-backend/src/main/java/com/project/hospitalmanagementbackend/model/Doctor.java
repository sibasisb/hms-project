package com.project.hospitalmanagementbackend.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.project.hospitalmanagementbackend.util.DoctorIdGenerator;
import com.project.hospitalmanagementbackend.util.HospitalIdGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="doctor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Doctor  {

	 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @GenericGenerator(
    	       name = "doctor_seq", 
    	       strategy = "com.project.hospitalmanagementbackend.util.StringPrefixedPatientIdGenerator", 
    	       parameters = {
    	           @Parameter(name = DoctorIdGenerator.INCREMENT_PARAM, value = "1"),
    	           @Parameter(name = DoctorIdGenerator.VALUE_PREFIX_PARAMETER, value = "DOC"),
    	           @Parameter(name = DoctorIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	 private String doctorId;
	
	@Column(name="qualification")
	private String qualification;
	
	@Column(name="speciality")
	private String speciality;
	
	@Column(name="experience")
	private int experience;
	
	@Column(name="availableDays")
	private String availableDays;
	
	@Column(name="availableTime")
	private String availableTime;
	
	@Column(name="charge")
	private BigDecimal charge;
	
	@ManyToMany
	@JoinTable(
			name="doctor_hospital",
			joinColumns = @JoinColumn(name = "doctor_id"),
			inverseJoinColumns = @JoinColumn(name="hospital_id"))
	private List<Hospital> hospital=new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	
	
	
}
