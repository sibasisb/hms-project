package com.project.hospitalmanagementbackend.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.hospitalmanagementbackend.dto.AppointmentInfo;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.service.AppointmentService;

@SpringBootTest
public class AppointmentControllerTest {

	@InjectMocks
	AppointmentController appointmentController;

	@Mock
	private AppointmentService appointmentService;

	@Test
	void getAllAppointmentByUserTestSuccess() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		Set<Appointment> appointments = new HashSet<>();
		appointments.add(appointment);
		AppointmentInfo appointmentInfo = new AppointmentInfo(LocalDate.of(2021, 02, 14),LocalTime.of(20, 04),"John Doe",null,"something");
		ArrayList<AppointmentInfo> appointmentList = new ArrayList<AppointmentInfo>();
		when(appointmentService.getAllAppointmentsByUser(patient.getPatientId())).thenReturn(appointmentList);
		
		assertEquals(appointmentController.getAllAppointmentByUser(patient.getPatientId()), new ResponseEntity<>(appointmentList, HttpStatus.OK));
	}
	
	@Test
	void bookAppointmentTestSuccess() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		
		when(appointmentService.bookAnAppointment(patient.getPatientId(), hospital.getHospitalId(), doctor.getDoctorId(), appointment)).thenReturn("created");
		
		assertEquals(appointmentController.bookAppointment(patient.getPatientId(), hospital.getHospitalId(), doctor.getDoctorId(), appointment), new ResponseEntity<>("created", HttpStatus.CREATED));
	}
	
	@Test
	public void testGetPendingAppointmentList() {
		Patient patient =  new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male", "7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null , new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs", "circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility , "desc", "rem", new BigDecimal(54.00));
		Appointment ap = new Appointment(121l, patient, doctor , hospital, hospitalFacility , LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		List<Appointment> appointmentList=new ArrayList<Appointment>();
		appointmentList.add(ap);
		List<AppointmentInfo> appointmentInfoList = new ArrayList<AppointmentInfo>();
		appointmentList.forEach((appointment->{
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentDate(appointment.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointment.getAppointmentTime());
			if(appointment.getHospitalFacility()==null)
				appointmentInfo.setDoctorName(appointment.getDoctor().getUser().getFirstName()+" "+appointment.getDoctor().getUser().getLastName());
			else
				appointmentInfo.setFacilityName(appointment.getHospitalFacility().getFacility().getName());
			
			appointmentInfo.setHospitalName(appointment.getHospital().getName());
			appointmentInfoList.add(appointmentInfo);
		}));
		String hospitalAdminId="HAD00001";
    	when(appointmentService.getPendingAppointents(hospitalAdminId)).thenReturn(appointmentInfoList);
    	assertEquals(appointmentController.getPendingAppointmentList(hospitalAdminId),new ResponseEntity<>(appointmentInfoList, HttpStatus.OK));
	}
	
}
