package com.project.hospitalmanagementbackend.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.project.hospitalmanagementbackend.util.HospitalIdGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="hospital")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Hospital {

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
    @GenericGenerator(
        name = "hospital_seq", 
        strategy = "com.project.hospitalmanagementbackend.util.HospitalIdGenerator", 
        parameters = {
            @Parameter(name = HospitalIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = HospitalIdGenerator.VALUE_PREFIX_PARAMETER, value = "HOS"),
            @Parameter(name =HospitalIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	String hospitalId;
	
	@Column(name="name")
	String name;
	
	@Column(name="address")
	String address;
	
	@Column(name="phone")
	String phone;
	
	@Column(name="website")
	String website;
	
	@ManyToMany(mappedBy = "hospital")
	List<Doctor> doctor=new ArrayList<>();
	
	@OneToMany(mappedBy = "hospital")
	List<HospitalAdmin> hospitalAdminId=new ArrayList<>();
	
}
