package com.project.hospitalmanagementbackend.exception;

public class InPatientNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InPatientNotFoundException(String msg) {
		super(msg);
	}

}
