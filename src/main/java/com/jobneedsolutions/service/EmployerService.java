package com.jobneedsolutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jobneedsolutions.entity.Employer;
import com.jobneedsolutions.repository.EmployerRepository;

@Service
public class EmployerService {

	@Autowired
	private EmployerRepository employerRepository;

	
	
	public ResponseEntity<?> addEmployer(Employer employer) {
	employerRepository.save(employer);	
	return new ResponseEntity<String>("Employer added successfully.",HttpStatus.OK);
	}

	public ResponseEntity<List<Employer>> getAllEmployers() {
		List<Employer> employers = employerRepository.findAll();
		return new ResponseEntity<List<Employer>>(employers,HttpStatus.OK);
	}
}
