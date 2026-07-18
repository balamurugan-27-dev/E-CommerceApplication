package com.balamurugan.ecommerce.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlopalException {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?>resoucreNotFoundException(ResourceNotFoundException e){
		
		return ResponseEntity.status(404)
				.body(e.getMessage());
		
	}
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> missingServletException(MissingServletRequestParameterException e){
		
		return ResponseEntity.status(400)
				.body("bad request check passing aruguments");
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<T>validationException(ConstraintViolationException e){
		return new ResponseEntity<T>("the mail is already registered");
	}
	
}
