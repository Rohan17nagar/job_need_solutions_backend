package com.jobneedsolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobneedsolutions.entity.Employer;
import com.jobneedsolutions.service.EmployerService;

@RestController
@RequestMapping("/employer")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployerController {

	@Autowired
	private EmployerService employerService;

	/**
	 * Get all employers
	 * 
	 * @return
	 */
	@GetMapping("/getAllEmployers")
	public ResponseEntity<List<Employer>> getAllEmployers() {
		return employerService.getAllEmployers();
	}

	/**
	 * Add employer
	 * 
	 * @param employer
	 * @return
	 */
	@PostMapping("/addEmployer")
	public ResponseEntity<?> addEmployer(@RequestBody Employer employer) {
		return employerService.addEmployer(employer);
	}
}
