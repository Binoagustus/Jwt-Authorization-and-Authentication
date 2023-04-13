package com.example.authdemo.DTO;

import org.springframework.stereotype.Component;

@Component
public class LoginDTO {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
