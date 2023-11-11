package com.jobneedsolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobneedsolutions.dto.GetJobPostForUserRequestDto;
import com.jobneedsolutions.entity.JobPost;
import com.jobneedsolutions.service.JobPostService;

@RestController
@RequestMapping("/jobPost")
@CrossOrigin(origins = "http://localhost:5173")
public class JobPostController {

	@Autowired
	private JobPostService jobPostService;

	/**
	 * Get all job posts
	 * 
	 * @return jobPosts
	 */
	@GetMapping("/getAllJobPosts")
	public ResponseEntity<?> getAllJobPosts() {
		return jobPostService.getAllJobPosts();
	}

	/**
	 * Add Job post
	 * 
	 * @param jobPost
	 * @return
	 */
	@PostMapping("/addJobPost")
	public ResponseEntity<?> addJobPost(@RequestBody JobPost jobPost) {
		return jobPostService.addJobPost(jobPost);
	}

	@GetMapping("/getJobAppsByPostId/{jobPostId}")
	public ResponseEntity<?> getJobAppsByPostId(@PathVariable int jobPostId) {
		return jobPostService.getJobAppsByPostId(jobPostId);
	}

	@PostMapping("/getJobPostForUser/{userId}")
	public ResponseEntity<?> getJobPostsForUser(@PathVariable long userId,
			@RequestBody GetJobPostForUserRequestDto getJobPostForUserRequestDto) {
		return jobPostService.getJobPostsForUser(userId, getJobPostForUserRequestDto);
	}

}
