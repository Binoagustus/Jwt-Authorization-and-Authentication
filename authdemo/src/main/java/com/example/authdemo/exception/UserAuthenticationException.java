package com.example.authdemo.exception;

public class UserAuthenticationException extends RuntimeException{

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public UserAuthenticationException(String message) {
		this.message = message;
	}
}
