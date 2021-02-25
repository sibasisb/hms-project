package com.project.hospitalmanagementbackend.dto;

import java.math.BigDecimal;
import java.util.List;

import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserInfo {
	
	private User user;
	private Hospital hospital;
	private Doctor doctor;

}
