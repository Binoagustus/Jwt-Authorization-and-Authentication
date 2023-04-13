package com.example.authdemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authdemo.DTO.LoginDTO;
import com.example.authdemo.exception.UserAuthenticationException;
import com.example.authdemo.model.ERole;
import com.example.authdemo.model.Role;
import com.example.authdemo.model.UserInfo;
import com.example.authdemo.repository.RoleRepository;
import com.example.authdemo.repository.UserRepository;
import com.example.authdemo.response.JwtResponse;
import com.example.authdemo.utility.JWTUtils;

@Service
public class AuthUserService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JWTUtils jwtUtils;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public void addUser(UserInfo signUpUser) {

		if (userRepository.existsByUsername(signUpUser.getUsername())) {
			throw new UserAuthenticationException("User Name already exists");
		}

		if (userRepository.existsByEmail(signUpUser.getEmail())) {
			throw new UserAuthenticationException("Email is already in use!");
		}

		signUpUser.setPassword(encoder.encode(signUpUser.getPassword()));

//		List<Role> strRoles = signUpUser.getRoles();
//
//		if (strRoles == null) {
//			Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			strRoles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch(role.getRoleName()) {
//				case "admin":
//					Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					strRoles.add(adminRole);
//
//					break;
//	
//				default:
//					Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					strRoles.add(userRole);
//				}
//			});
//		}
//
//		signUpUser.setRoles(strRoles);
		userRepository.save(signUpUser);
	}

	public JwtResponse loginUser(LoginDTO login) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserdetailsImpl userDetails = (UserdetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt, userDetails.getUserId(), userDetails.getUsername(), userDetails.getEmail(), roles);
	}
}
