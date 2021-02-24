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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hospital_admin_id")
	Long hospitalAdminId;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="hospital_id")
    Hospital hospital; 
	
	
}
