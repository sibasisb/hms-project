package com.project.hospitalmanagementbackend.exception;

public class PatientNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8003565791986972112L;

	public PatientNotFoundException(String message) {
		super(message);
	}

}
