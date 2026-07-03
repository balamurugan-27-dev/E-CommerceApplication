package com.balamurugan.ecommerce.Exceptions;



public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8059086531431117269L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
