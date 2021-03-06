package com.project.hospitalmanagementbackend.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hospital_facility")
@JsonIgnoreProperties(value = { "hospital" })
public class HospitalFacility {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hospital_facility_id")
	private long hospitalFacilityId;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	@OneToOne
	@JoinColumn(name = "facility_id")
	private Facility facility;

	@Column(name = "description")
	private String description;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "charges")
	private BigDecimal charges;

}
