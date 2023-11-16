package com.jobneedsolutions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobneedsolutions.entity.UserEntity;
import com.jobneedsolutions.repository.UserRepository;

//import com.jwt.springboot.entity.User;
//import com.jwt.springboot.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found."));
		return  user;
	}

}
