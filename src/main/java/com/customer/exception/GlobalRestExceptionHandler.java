package com.customer.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalRestExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<?> customerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
		ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DateRangeException.class)
	public ResponseEntity<?> dateRangeException(DateRangeException ex, WebRequest request) {
		ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
