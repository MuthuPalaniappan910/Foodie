package com.healthy.foodie.exception;

public class NoVendorAvailableException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public NoVendorAvailableException(String message) {
		super(message);
	}
}
