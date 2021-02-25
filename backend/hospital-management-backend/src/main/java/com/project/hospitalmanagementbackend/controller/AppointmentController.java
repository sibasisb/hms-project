package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("/pending")
	public ResponseEntity<?> getPendingAppointmentList(@RequestBody Appointment appointment){
		List<Appointment> appointmentList=appointmentService.getPendingAppointents();
		return new ResponseEntity<>(appointmentList, HttpStatus.OK);
	}
	
}