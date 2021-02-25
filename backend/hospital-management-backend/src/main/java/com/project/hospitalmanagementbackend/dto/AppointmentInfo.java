package com.project.hospitalmanagementbackend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.model.Patient;

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
public class AppointmentInfo {

	LocalDate appointmentDate;
	LocalTime appointmentTime;
	String doctorName;
	String facilityName;
	String hospitalName;
}
