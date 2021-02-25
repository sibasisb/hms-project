package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("/pending/{hospitalAdminId}")
	public ResponseEntity<?> getPendingAppointmentList(@PathVariable("hospitalAdminId") Long hospitalAdminId){
		List<Appointment> appointmentList=appointmentService.getPendingAppointents(hospitalAdminId);
		return new ResponseEntity<>(appointmentList, HttpStatus.OK);
	}
	
}