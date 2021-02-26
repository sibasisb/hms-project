package com.project.hospitalmanagementbackend.exception;

public class DoctorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3803965714489044039L;

	public DoctorNotFoundException(String message) {
		super(message);
	}

}
