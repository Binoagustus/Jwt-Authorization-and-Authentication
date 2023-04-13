package com.example.authdemo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.authdemo.response.MessageResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = UserAuthenticationException.class)
	public MessageResponse userFileException(UserAuthenticationException exception) {
		return new MessageResponse(exception.getMessage());
	}
}
