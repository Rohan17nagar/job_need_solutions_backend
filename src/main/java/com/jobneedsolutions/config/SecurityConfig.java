package com.jobneedsolutions.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.Customizer;
import com.jobneedsolutions.entity.Role;
import com.jobneedsolutions.security.JwtAuthenticationEntryPoint;
import com.jobneedsolutions.security.JwtAuthenticationFilter;

import ch.qos.logback.classic.Logger;

//import com.jwt.springboot.entity.Role;
//import com.jwt.springboot.security.JwtAuthenticationEntryPoint;
//import com.jwt.springboot.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("http req"+http);
System.out.println("filter>>>"+filter);
		System.out.println("roless>>>>"+Role.USER.name());
		
		http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/login").permitAll()
				.requestMatchers("/auth/create-user").permitAll()
				.requestMatchers("/jobPost/**")
				.hasAnyAuthority(Role.ADMIN.name())
				.anyRequest().authenticated())
		.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		
		
//		http.csrf(csrf -> csrf.disable()).cors(cors ->cors.disable())
//				.authorizeHttpRequests(auth -> auth.requestMatchers("/home/**").
//						hasAnyAuthority(Role.ADMIN.name())
//						.requestMatchers("/jobPost/**")
//						.hasAuthority("USER")
//						.requestMatchers("/auth/login").permitAll()
//						
//						.requestMatchers("/auth/create-user").permitAll()
////						.permitAll()
//						.anyRequest().authenticated())
//				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}    

	// OLD USEFULL code::

	// http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
	// .authorizeHttpRequests(auth ->
	// auth.requestMatchers("/home/**").authenticated()
	// .requestMatchers("/auth/login").permitAll().requestMatchers("/auth/create-user").permitAll()
	// .anyRequest().authenticated())
	// .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
	// .sessionManagement(session ->
	// session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	//
	// http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	// return http.build();

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration   = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST"));
		corsConfiguration.setAllowedHeaders(List.of("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	
}
