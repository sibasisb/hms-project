package com.project.hospitalmanagementbackend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalmanagementbackend.dto.AppointmentInfo;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;

	@GetMapping("/{patientId}")
	public ResponseEntity<?> getAllAppointmentByUser(@PathVariable String patientId) {
		List<AppointmentInfo> appointments = appointmentService.getAllAppointmentsByUser(patientId);
		ResponseEntity<?> entity = new ResponseEntity<>(appointments, HttpStatus.OK);
		// appointments.forEach((appointment) -> log.info(appointment.toString()));
		// log.info(entity.toString());
		return entity;
	}

	@PostMapping("/{patientId}/{hospitalId}/{serviceId}")
	public ResponseEntity<String> bookAppointment(@PathVariable String patientId, @PathVariable String hospitalId,
			@PathVariable String serviceId, @RequestBody Appointment appointment) {
		return new ResponseEntity<>(appointmentService.bookAnAppointment(patientId, hospitalId, serviceId, appointment),
				HttpStatus.CREATED);
	}

	@GetMapping("/pending/{hospitalAdminId}")
	public ResponseEntity<?> getPendingAppointmentList(@PathVariable("hospitalAdminId") String hospitalAdminId) {
		List<AppointmentInfo> appointmentList = appointmentService.getPendingAppointents(hospitalAdminId);
		return new ResponseEntity<>(appointmentList, HttpStatus.OK);
	}

}
