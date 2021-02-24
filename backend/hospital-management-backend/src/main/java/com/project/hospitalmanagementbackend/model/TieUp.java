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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tie_ups")
public class TieUp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tie_up_id")
	private Long tieUpId;

	@ManyToOne
	@JoinColumn(name = "hospital_id_1")
	private Hospital hospital1;

	@ManyToOne
	@JoinColumn(name = "hospital_id_2")
	private Hospital hospital2;

}
