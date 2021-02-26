package com.project.hospitalmanagementbackend.exception;

public class HospitalNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2396902191539424729L;

	public HospitalNotFoundException(String message) {
		super(message);
	}

}
