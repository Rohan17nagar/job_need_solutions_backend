package com.jobneedsolutions.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobneedsolutions.entity.JwtRequest;
import com.jobneedsolutions.entity.JwtResponse;
import com.jobneedsolutions.entity.UserEntity;
import com.jobneedsolutions.repository.UserRepository;
import com.jobneedsolutions.security.JwtHelper;
import com.jobneedsolutions.service.UserService;

//import com.jwt.springboot.entity.JwtRequest;
//import com.jwt.springboot.entity.JwtResponse;
//import com.jwt.springboot.entity.User;
//import com.jwt.springboot.repositories.UserRepository;
//import com.jwt.springboot.security.JwtHelper;
//import com.jwt.springboot.service.UserService;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtHelper helper;

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest request) {
		this.doAuthenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);

		UserEntity user = userRepository.findByEmail(request.getEmail()).orElse(null);

		JwtResponse jwtResponse = new JwtResponse();
		if (user != null) {
			jwtResponse.setJwtToken(token);
			jwtResponse.setUserId(user.getUserId());
			jwtResponse.setEmail(user.getEmail());
			jwtResponse.setFirstName(user.getFirstName());
			jwtResponse.setLastName(user.getLastName());
			jwtResponse.setRole(user.getRole().name());
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}

//	        JwtResponse response = JwtResponse.builder()
//	                .jwtToken(token)
//	                .username(userDetails.getUsername())   
//	                .build();
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password dddd !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> exceptionHandler() {
		return new ResponseEntity<String>("Credentials Invalid 1223333!!", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody UserEntity user) {
		return userService.createUser(user);
	}

}
