package com.jobneedsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobneedsolutions.service.JobApplicationService;

@RestController
@RequestMapping("/jobApplications")
@CrossOrigin(origins = "http://localhost:5173")
public class JobApplicationController {

	@Autowired
	private JobApplicationService jobApplicationService;

	@GetMapping("/applyForJob/{jobPostId}/{userId}")
	public ResponseEntity<String> applyForJob(@PathVariable int jobPostId, @PathVariable long userId) {
		return jobApplicationService.applyForJob(jobPostId, userId);
	}
}
