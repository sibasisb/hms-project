package com.project.hospitalmanagementbackend.exception;

public class AppointmentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4941514140675073241L;

	public AppointmentNotFoundException(String message) {
		super(message);
	}

}
