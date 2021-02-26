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

import com.project.hospitalmanagementbackend.dto.AppointmentInfo;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;

	@GetMapping("/{patientId}")
	public ResponseEntity<?> getAllAppointmentByUser(@PathVariable String patientId) {
		List<AppointmentInfo> appointments = appointmentService.getAllAppointmentsByUser(patientId);
		ResponseEntity<?> entity = new ResponseEntity<>(appointments, HttpStatus.OK);
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
		List<Appointment> appointmentList = appointmentService.getPendingAppointents(hospitalAdminId);
		return new ResponseEntity<>(appointmentList, HttpStatus.OK);
	}

	@GetMapping("/view/doctor/{doctorId}")
	public ResponseEntity<List<AppointmentInfo>> getAllAppointmentsByDoctor(@PathVariable String doctorId) {
		return new ResponseEntity<>(appointmentService.getAllAppointmentsByDoctor(doctorId), HttpStatus.OK);
	}

	@GetMapping("/view/facility/{hospitalFacilityId}")
	public ResponseEntity<List<AppointmentInfo>> getAllAppointmentsByFacility(@PathVariable long hospitalFacilityId) {
		return new ResponseEntity<>(appointmentService.getAllAppointmentsByFacility(hospitalFacilityId), HttpStatus.OK);
	}
	
	@GetMapping("/approve/{appointmentId}")
	public ResponseEntity<String> approveAppointment(@PathVariable Long appointmentId) {
		return new ResponseEntity<>(appointmentService.approveAppointment(appointmentId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/reject/{appointmentId}")
	public ResponseEntity<String> rejectAppointment(@PathVariable Long appointmentId) {
		return new ResponseEntity<>(appointmentService.rejectAppointment(appointmentId), HttpStatus.ACCEPTED);
		
	}
}
