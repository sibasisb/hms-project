package com.project.hospitalmanagementbackend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.project.hospitalmanagementbackend.util.DoctorIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="hospital_admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HospitalAdmin {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_admin_seq")
    @GenericGenerator(
    	       name = "hospital_admin_seq", 
    	       strategy = "com.project.hospitalmanagementbackend.util.StringPrefixedPatientIdGenerator", 
    	       parameters = {
    	           @Parameter(name = DoctorIdGenerator.INCREMENT_PARAM, value = "1"),
    	           @Parameter(name = DoctorIdGenerator.VALUE_PREFIX_PARAMETER, value = "HAD"),
    	           @Parameter(name = DoctorIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	private String hospitalAdminId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="hospital_id")
	private Hospital hospital; 
	
	
}
