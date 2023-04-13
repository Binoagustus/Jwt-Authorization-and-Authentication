package com.example.authdemo.response;

public class MessageResponse {

	private String message;
	private Object object;

	public MessageResponse(String message) {
		this.message = message;
	}

	public MessageResponse(String message, Object object) {
		this.message = message;
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
