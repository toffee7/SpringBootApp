package com.toffee.home.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.toffee.home.exception.EmployeeServiceException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { EmployeeServiceException.class })
	public ResponseEntity<Object> verifySoeidException(EmployeeServiceException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getStatusCode()));
	}
}
