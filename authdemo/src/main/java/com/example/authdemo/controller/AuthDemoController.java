package com.example.authdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.authdemo.DTO.LoginDTO;
import com.example.authdemo.model.UserInfo;
import com.example.authdemo.response.JwtResponse;
import com.example.authdemo.response.MessageResponse;
import com.example.authdemo.service.AuthUserService;

@RestController("/auth")
public class AuthDemoController {

	@Autowired
	AuthUserService service;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@RequestBody UserInfo signUpUser) {
		service.addUser(signUpUser);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<MessageResponse> loginUser(@RequestBody LoginDTO login) {
		JwtResponse response = service.loginUser(login);
		MessageResponse messageResponse = new MessageResponse("User loggedIn succesfully", response);
		return new ResponseEntity<>(messageResponse, HttpStatus.OK);
	}
}
