package com.project.hospitalmanagementbackend.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "facility")
public class Facility {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="facility_id")
	private long facilityId;
	
	@Column(name="facility_name")
	private String name;
	
	@Column(name="values")
	private HashMap<String,String> baselineValues;

}
