package com.project.hospitalmanagementbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "sys_admins")
public class SystemAdmin {

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "password")
	private String password;

}
