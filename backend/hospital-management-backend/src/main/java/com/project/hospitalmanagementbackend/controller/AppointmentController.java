package com.project.hospitalmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;

	@GetMapping("/{patientId}")
	public ResponseEntity<List<Appointment>> getAllAppointmentByUser(@PathVariable String patientId) {
		return new ResponseEntity<>(appointmentService.getAllAppointmentsByUser(patientId), HttpStatus.OK);
	}

	@PostMapping("/{patientId}/{hospitalId}/{serviceId}")
	public ResponseEntity<String> bookAppointment(@PathVariable String patientId,@PathVariable String hospitalId,@PathVariable String serviceId,@RequestBody Appointment appointment) {
		return new ResponseEntity<>(appointmentService.bookAnAppointment(patientId, hospitalId, serviceId, appointment), HttpStatus.CREATED);
	}

}
