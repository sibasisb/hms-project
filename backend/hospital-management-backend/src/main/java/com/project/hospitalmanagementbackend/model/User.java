package com.project.hospitalmanagementbackend.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	Long userId;
	
	@Column(name = "fname")
	String firstName;
	
	@Column(name = "lname")
	String lastName;
	
	@Column(name = "dob")
	LocalDate dateOfBirth;
	
	@Column(name = "gender")
	String gender;
	
	@Column(name = "contact")
	String contact;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "role")
	String role;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(
			joinColumns = @JoinColumn(name="user_id"))
	Map<Long,String> securityAnwswers=new HashMap<Long,String>();
	
	
}
