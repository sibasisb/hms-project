package com.project.hospitalmanagementbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.hospitalmanagementbackend.dto.AppointmentInfo;
import com.project.hospitalmanagementbackend.model.Appointment;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.Facility;
import com.project.hospitalmanagementbackend.model.Hospital;
import com.project.hospitalmanagementbackend.model.HospitalAdmin;
import com.project.hospitalmanagementbackend.model.HospitalFacility;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.AppointmentRepository;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;
import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;
import com.project.hospitalmanagementbackend.repository.HospitalFacilityRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;

import com.project.hospitalmanagementbackend.repository.PatientRepository;

@SpringBootTest
public class AppointmentServiceTest {

	@InjectMocks
	AppointmentService appointmentService;

	@Mock
	AppointmentRepository appointmentRepository;

	@Mock
	PatientRepository patientRepository;

	@Mock
	HospitalRepository hospitalRepository;

	@Mock
	DoctorRepository doctorRepository;

	@Mock
	HospitalFacilityRepository hospitalFacilityRepository;

	@Mock
	private HospitalAdminRepository hospitalAdminRepository;

	@Test
	public void testGetPendingAppointents() {
		Patient patient = new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male",
				"7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null,
				new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs",
						"circiut", "Doctor"));
		Facility facility = new Facility(4l, null, null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility, "desc", "rem",
				new BigDecimal(54.00));
		Appointment a = new Appointment(121l, patient, doctor, hospital, hospitalFacility, LocalDate.of(2021, 02, 14),
				LocalTime.of(20, 04), "hem", null, true, false);
		List<AppointmentInfo> appointmentInfoList = new ArrayList<AppointmentInfo>();
		List<Appointment> appointments = appointmentRepository
				.findPatientsWithFacilityRequests(hospital.getHospitalId());
		appointments.add(a);
		appointments.forEach((appointment -> {
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentDate(appointment.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointment.getAppointmentTime());
			appointmentInfo.setHospitalName(appointment.getHospital().getName());
			appointmentInfoList.add(appointmentInfo);
		}));
		String hospitalAdminId = "HAD00001";
		when(hospitalAdminRepository.findById(hospitalAdminId)).thenReturn(Optional.of(new HospitalAdmin()));
		when(appointmentRepository.findPatientsWithFacilityRequests(hospital.getHospitalId())).thenReturn(appointments);
		when(hospitalAdminRepository.getHospitalIdByAdminId(hospitalAdminId)).thenReturn(hospital.getHospitalId());
		assertEquals(appointmentService.getPendingAppointents(hospitalAdminId).size(), appointmentInfoList.size());
	}

	@Test
	void getAllAppointmentsByUserTestSuccess() {
		Patient patient = new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male",
				"7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null,
				new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs",
						"circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility, "desc", "rem",
				new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor, hospital, hospitalFacility,
				LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		Set<Appointment> appointments = new HashSet<>();
		appointments.add(appointment);

		List<AppointmentInfo> appointmentInfoList = new ArrayList<>();
		appointments.forEach((appointmentV -> {
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentDate(appointmentV.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointmentV.getAppointmentTime());
			if (appointmentV.getHospitalFacility() == null)
				appointmentInfo.setDoctorName(appointmentV.getDoctor().getUser().getFirstName() + " "
						+ appointmentV.getDoctor().getUser().getLastName());
			else
				appointmentInfo.setFacilityName(appointmentV.getHospitalFacility().getFacility().getName());

			appointmentInfo.setHospitalName(appointmentV.getHospital().getName());
			appointmentInfoList.add(appointmentInfo);
		}));

		when(patientRepository.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		when(appointmentRepository.getAllAppointmentsByPatient(patient.getPatientId())).thenReturn(appointments);

		List<AppointmentInfo> expected = appointmentService.getAllAppointmentsByUser(patient.getPatientId());
		List<AppointmentInfo> actual = appointmentInfoList;
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i).getAppointmentDate(), actual.get(i).getAppointmentDate());
			assertEquals(expected.get(i).getAppointmentTime(), actual.get(i).getAppointmentTime());
			assertEquals(expected.get(i).getHospitalName(), actual.get(i).getHospitalName());
			assertEquals(expected.get(i).getDoctorName(), actual.get(i).getDoctorName());
			assertEquals(expected.get(i).getFacilityName(), actual.get(i).getFacilityName());
		}

	}

	@Test
	void bookDoctorAppointmentTestSuccess() {
		Patient patient = new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male",
				"7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null,
				new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs",
						"circiut", "Doctor"));
		Appointment appointment = new Appointment(121l, patient, null, hospital, null, LocalDate.of(2021, 02, 14),
				LocalTime.of(20, 04), "hem", null, true, false);

		when(patientRepository.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		when(hospitalRepository.findById(hospital.getHospitalId())).thenReturn(Optional.of(hospital));
		when(doctorRepository.findById(doctor.getDoctorId())).thenReturn(Optional.of(doctor));
		when(appointmentRepository.save(appointment)).thenReturn(appointment);

		assertEquals(appointmentService.bookAnAppointment(patient.getPatientId(), hospital.getHospitalId(),
				doctor.getDoctorId(), appointment), "created");
	}

	@Test
	void bookFacilityAppointmentTestSuccess() {
		Patient patient = new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male",
				"7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility, "desc", "rem",
				new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, null, hospital, null, LocalDate.of(2021, 02, 14),
				LocalTime.of(20, 04), "hem", null, true, false);

		when(patientRepository.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		when(hospitalRepository.findById(hospital.getHospitalId())).thenReturn(Optional.of(hospital));
		when(hospitalFacilityRepository.findById(hospitalFacility.getHospitalFacilityId()))
				.thenReturn(Optional.of(hospitalFacility));
		when(appointmentRepository.save(appointment)).thenReturn(appointment);

		assertEquals(appointmentService.bookAnAppointment(patient.getPatientId(), hospital.getHospitalId(),
				String.valueOf(hospitalFacility.getHospitalFacilityId()), appointment), "created");
	}

	@Test
	void getAllAppointmentsByDoctorTestSuccess() {
		Patient patient = new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male",
				"7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null,
				new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs",
						"circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility, "desc", "rem",
				new BigDecimal(54.00));
		Appointment appointmentN = new Appointment(121l, patient, doctor, hospital, hospitalFacility,
				LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		List<Appointment> appointments = new ArrayList<>();
		appointments.add(appointmentN);

		List<AppointmentInfo> appointmentInfoList = new ArrayList<AppointmentInfo>();
		appointments.forEach((appointment -> {
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentId(appointment.getAppointmentId());
			appointmentInfo.setPatientName(appointment.getPatient().getUser().getFirstName() + " "
					+ appointment.getPatient().getUser().getLastName());
			appointmentInfo.setAppointmentDate(appointment.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointment.getAppointmentTime());
			appointmentInfo.setDoctorName(appointment.getDoctor().getUser().getFirstName() + " "
					+ appointment.getDoctor().getUser().getLastName());

			appointmentInfo.setHospitalName(appointment.getHospital().getName());
			appointmentInfo.setRemarks(appointment.getRemarks());
			appointmentInfo.setMedicalRecords(appointment.getMedicalRecords());
			appointmentInfoList.add(appointmentInfo);
		}));

		when(doctorRepository.findById(doctor.getDoctorId())).thenReturn(Optional.of(doctor));
		when(appointmentRepository.findByDoctor_DoctorId(doctor.getDoctorId())).thenReturn(appointments);

		List<AppointmentInfo> expected = appointmentService.getAllAppointmentsByDoctor(doctor.getDoctorId());
		List<AppointmentInfo> actual = appointmentInfoList;
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i).getAppointmentDate(), actual.get(i).getAppointmentDate());
			assertEquals(expected.get(i).getAppointmentTime(), actual.get(i).getAppointmentTime());
			assertEquals(expected.get(i).getHospitalName(), actual.get(i).getHospitalName());
			assertEquals(expected.get(i).getDoctorName(), actual.get(i).getDoctorName());
			assertEquals(expected.get(i).getFacilityName(), actual.get(i).getFacilityName());
		}
	}

	@Test
	void getAllAppointmentsByHospitalAdminTestSuccess() {
		Patient patient = new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male",
				"7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null,
				new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs",
						"circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility, "desc", "rem",
				new BigDecimal(54.00));
		Set<HospitalFacility> facilities = new HashSet<>();
		facilities.add(hospitalFacility);
		hospital.setHospitalFacilities(facilities);
		HospitalAdmin hospitalAdmin = new HospitalAdmin();
		hospitalAdmin.setHospital(hospital);
		Appointment appointmentN = new Appointment(121l, patient, doctor, hospital, hospitalFacility,
				LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);
		List<Appointment> appointments = new ArrayList<>();
		appointments.add(appointmentN);

		List<AppointmentInfo> appointmentInfoList = new ArrayList<AppointmentInfo>();
		appointments.forEach((appointment -> {
			AppointmentInfo appointmentInfo = new AppointmentInfo();
			appointmentInfo.setAppointmentId(appointment.getAppointmentId());
			appointmentInfo.setPatientName(appointment.getPatient().getUser().getFirstName() + " "
					+ appointment.getPatient().getUser().getLastName());
			appointmentInfo.setAppointmentDate(appointment.getAppointmentDate());
			appointmentInfo.setAppointmentTime(appointment.getAppointmentTime());
			appointmentInfo.setFacilityName(appointment.getHospitalFacility().getFacility().getName());
			appointmentInfo.setHospitalName(appointment.getHospital().getName());
			appointmentInfo.setRemarks(appointment.getRemarks());
			appointmentInfo.setMedicalRecords(appointment.getMedicalRecords());
			appointmentInfoList.add(appointmentInfo);
		}));

		when(hospitalAdminRepository.findById("HOS001")).thenReturn(Optional.of(hospitalAdmin));
		when(hospitalRepository.findById(hospitalAdmin.getHospital().getHospitalId()))
				.thenReturn(Optional.of(hospital));
		when(appointmentRepository.findByHospitalFacility_HospitalFacilityId(hospitalFacility.getHospitalFacilityId()))
				.thenReturn(appointments);

		List<AppointmentInfo> expected = appointmentService.getAllAppointmentsByHospitalAdmin("HOS001");
		List<AppointmentInfo> actual = appointmentInfoList;
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i).getAppointmentDate(), actual.get(i).getAppointmentDate());
			assertEquals(expected.get(i).getAppointmentTime(), actual.get(i).getAppointmentTime());
			assertEquals(expected.get(i).getHospitalName(), actual.get(i).getHospitalName());
			assertEquals(expected.get(i).getDoctorName(), actual.get(i).getDoctorName());
			assertEquals(expected.get(i).getFacilityName(), actual.get(i).getFacilityName());
		}
	}

	@Test
	public void approveAppointmentTestSuccess() {
		Patient patient = new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male",
				"7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null,
				new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs",
						"circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility, "desc", "rem",
				new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor, hospital, hospitalFacility,
				LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);

		when(appointmentRepository.findById(appointment.getAppointmentId())).thenReturn(Optional.of(appointment));

		assertEquals(appointmentService.approveAppointment(appointment.getAppointmentId()), "approved");
	}

	@Test
	public void rejectAppointmentTestSuccess() {
		Patient patient = new Patient("PAT001", new User(1l, "John", "Doe", LocalDate.of(1985, 5, 25), "Male",
				"7894561230", "john@doe.com", "incorrect", "patient"));
		Hospital hospital = new Hospital("HOS001", "something", "on Earth", "8450351976", "www.earth.com", null, null);
		Doctor doctor = new Doctor("DOC001", "M. B. B. S.", "cardio", 5, "Monday", "5:00", new BigDecimal(250.00), null,
				new User(2l, "Munna", "Bhai", LocalDate.of(1968, 8, 4), "male", "8459872650", "munna@bhai.mbbs",
						"circiut", "Doctor"));
		Facility facility = new Facility(4l, "this", null);
		HospitalFacility hospitalFacility = new HospitalFacility(3l, hospital, facility, "desc", "rem",
				new BigDecimal(54.00));
		Appointment appointment = new Appointment(121l, patient, doctor, hospital, hospitalFacility,
				LocalDate.of(2021, 02, 14), LocalTime.of(20, 04), "hem", null, true, false);

		when(appointmentRepository.findById(appointment.getAppointmentId())).thenReturn(Optional.of(appointment));

		assertEquals(appointmentService.rejectAppointment(appointment.getAppointmentId()), "rejected");
	}
}
