package com.project.hospitalmanagementbackend.dto;


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
public class HospitalInfo {
	
	private String hospitalId;
	private String name;
	private String address;
	private String phone;
	private String website;


}
