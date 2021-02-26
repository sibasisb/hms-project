package com.project.hospitalmanagementbackend.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.java.Log;

@Log
@ControllerAdvice
public class CustomGloabalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.info("Start");

		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(

			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,

			WebRequest request) {

		log.info("Start");
		Map<String, Object> body = new LinkedHashMap<>();

		body.put("timestamp", new Date());

		body.put("status", status.value());

		body.put("error", "Bad Request");

		List<String> errors = new ArrayList<String>();

		if (ex.getCause() instanceof InvalidFormatException) {

			final Throwable cause = ex.getCause() == null ? ex : ex.getCause();

			for (InvalidFormatException.Reference reference : ((InvalidFormatException) cause).getPath()) {

				body.put("message", "Incorrect format for field '" + reference.getFieldName() + "'");

			}

		}

		return new ResponseEntity<>(body, headers, status);

	}

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<?> springHandleInvalidUser(InvalidUserException ex) throws IOException {

		Map<String, Object> body = new LinkedHashMap<>();

		body.put("timestamp", new Date());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<?> springHandlePatientNotFoundException(PatientNotFoundException ex) throws IOException {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TestResultNotFoundException.class)
	public ResponseEntity<?> springHandleTestResultNotFoundException(TestResultNotFoundException ex) throws IOException {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

}
