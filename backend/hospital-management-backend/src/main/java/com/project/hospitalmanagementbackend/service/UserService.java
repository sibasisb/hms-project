package com.project.hospitalmanagementbackend.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.hospitalmanagementbackend.dto.AuthRequestUser;
import com.project.hospitalmanagementbackend.dto.AuthResponseUser;
import com.project.hospitalmanagementbackend.dto.UserInfo;
import com.project.hospitalmanagementbackend.exception.InvalidUserException;
import com.project.hospitalmanagementbackend.model.Doctor;
import com.project.hospitalmanagementbackend.model.HospitalAdmin;
import com.project.hospitalmanagementbackend.model.Patient;
import com.project.hospitalmanagementbackend.model.SystemAdmin;
import com.project.hospitalmanagementbackend.model.User;
import com.project.hospitalmanagementbackend.repository.DoctorRepository;
import com.project.hospitalmanagementbackend.repository.HospitalAdminRepository;
import com.project.hospitalmanagementbackend.repository.HospitalRepository;
import com.project.hospitalmanagementbackend.repository.PatientRepository;
import com.project.hospitalmanagementbackend.repository.SystemAdminRepository;
import com.project.hospitalmanagementbackend.repository.UserRepository;
import com.project.hospitalmanagementbackend.util.BlacklistedTokenHandler;
import com.project.hospitalmanagementbackend.util.JwtUtil;

import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.extern.java.Log;

@Log
@Service
public class UserService implements UserDetailsService {

	@Autowired
	JwtUtil jwtutil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	SystemAdminRepository sysAdminRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	HospitalAdminRepository hospitalAdminRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	HospitalRepository hospitalRepository;

	@Autowired
	BlacklistedTokenHandler blacklistTokenHandler;
	
	String userType="user";

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password = "";
		String role = "";
		if (username.length() > 3 && userType.equals("user")) {
			String userPrefix = username.substring(0, 3);
			switch (userPrefix) {
			case "PAT": {
				Optional<Patient> patient = patientRepository.findById(username);
				if (patient.isPresent()) {
					User userFound = patient.get().getUser();
					role = userFound.getRole();
					password = userFound.getPassword();
				}
				break;
			}
			case "DOC": {
				Optional<Doctor> doctor = doctorRepository.findById(username);
				if (doctor.isPresent()) {
					User userFound = doctor.get().getUser();
					role = userFound.getRole();
					password = userFound.getPassword();
				}
				break;
			}
			case "HAD": {
				Optional<HospitalAdmin> hospitalAdmin = hospitalAdminRepository.findById(username);
				if (hospitalAdmin.isPresent()) {
					User userFound = hospitalAdmin.get().getUser();
					role = userFound.getRole();
					password = userFound.getPassword();
				}
				break;
			}
			}
		}
		if ((role.equals("") || password.equals("")) && userType.equals("admin")) {
			SystemAdmin systemAdmin = null;
			Optional<SystemAdmin> admin = sysAdminRepository.findById(username);
			if (admin.isPresent()) {
				systemAdmin = admin.get();
				role = "admin";
				password = systemAdmin.getPassword();
			}
		}

		if (role.equals("") || password.equals(""))
			throw new InvalidUserException("Invalid UserId/Password");
		else {
			ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
			list.add(new SimpleGrantedAuthority(role));
			return new org.springframework.security.core.userdetails.User(username, password, list);

		}
	}

	public String authenticate(AuthRequestUser user) {
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword()));
		} catch (Exception e) {
			throw new InvalidUserException("Invalid UserId/Password");
		}

		return jwtutil.generateToken(user.getUserId());

	}

	@Transactional
	public AuthResponseUser getAdmin(SystemAdmin sysadmin) {
		// TODO Auto-generated method stub
		userType="admin";
		String token = authenticate(new AuthRequestUser(sysadmin.getUserId(), sysadmin.getPassword()));
		blacklistTokenHandler.unsetBlacklistToken(token);

		return new AuthResponseUser(sysadmin.getUserId(), null, "admin", token);
	}

	@Transactional
	public AuthResponseUser getUser(AuthRequestUser user) {
		// TODO Auto-generated method stub
		userType="user";
		String username = "";
		String role = "";
		if (user.getUserId().length() > 3) {
			String userPrefix = user.getUserId().substring(0, 3);
			if (userPrefix.equals("PAT") || userPrefix.equals("DOC") || userPrefix.equals("HAD")) {
				String token = authenticate(user);
				blacklistTokenHandler.unsetBlacklistToken(token);
				switch (userPrefix) {
				case "PAT": {
					User userInfo = patientRepository.findById(user.getUserId()).get().getUser();
					username = userInfo.getFirstName() + " " + userInfo.getLastName();
					role = userInfo.getRole();

					break;
				}
				case "DOC": {
					User userInfo = doctorRepository.findById(user.getUserId()).get().getUser();
					username = userInfo.getFirstName() + " " + userInfo.getLastName();
					role = userInfo.getRole();
					break;
				}
				case "HAD": {
					User userInfo = hospitalAdminRepository.findById(user.getUserId()).get().getUser();
					username = userInfo.getFirstName() + " " + userInfo.getLastName();
					role = userInfo.getRole();
					break;
				}

				default:
					break;
				}
				return new AuthResponseUser(user.getUserId(), username, role, token);
			} else
				throw new InvalidUserException("Invalid UserId/Password");
		}

		return null;

	}

	@Transactional
	public String register(UserInfo userInfo) {
		// TODO Auto-generated method stub
		log.info(userInfo.toString());
		String role = userInfo.getUser().getRole();
		String userId = "";
		switch (role) {
		case "doctor": {
			Doctor doctor = userInfo.getDoctor();
			doctor.setUser(userInfo.getUser());
			doctor.getHospital().add(hospitalRepository.findById(userInfo.getHospital().getHospitalId()).get());
			Doctor savedDoctor = doctorRepository.save(doctor);
			userId = savedDoctor.getDoctorId();
			break;
		}
		case "hospital admin": {
			log.info("something");
			HospitalAdmin hosAdmin = new HospitalAdmin();
			hosAdmin.setHospital(userInfo.getHospital());
			hosAdmin.setUser(userInfo.getUser());
			HospitalAdmin savedHospitalAdmin = hospitalAdminRepository.save(hosAdmin);
			userId = savedHospitalAdmin.getHospitalAdminId();
			break;
		}
		default: {
			Patient patient = new Patient();
			patient.setUser(userInfo.getUser());
			Patient savedPatient = patientRepository.save(patient);
			userId = savedPatient.getPatientId();
		}
		}
		return "User Registered Successfully.Your User ID is " + userId;

	}

	@Transactional
	public String logout(String header) {
		SecurityContextHolder.getContext().setAuthentication(null);
		String token = header.substring(7);
		blacklistTokenHandler.setBlacklistToken(token);
		return "User logged out";
	}

}
