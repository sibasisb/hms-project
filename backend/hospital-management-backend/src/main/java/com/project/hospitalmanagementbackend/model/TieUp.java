package com.project.hospitalmanagementbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class TieUp {
	
	@Id
	private Long tieUpId;
	
	// TODO: add missing model associations
	// private Hospital hospital1
	
	// private Hospital hospital2

}
