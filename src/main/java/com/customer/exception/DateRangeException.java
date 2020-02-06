package com.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateRangeException extends Exception {

	private static final long serialVersionUID = 1L;

	public DateRangeException(String message){
		super(message);
	}
}
