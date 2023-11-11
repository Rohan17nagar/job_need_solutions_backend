package com.jobneedsolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jobneedsolutions.entity.JobApplications;
import com.jobneedsolutions.entity.JobPost;
import com.jobneedsolutions.entity.UserEntity;
import com.jobneedsolutions.repository.JobApplicationRepository;
import com.jobneedsolutions.repository.JobPostRepository;
import com.jobneedsolutions.repository.UserRepository;

@Service
public class JobApplicationService {

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Autowired
	private JobPostRepository jobPostRepository;

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<String> applyForJob(int jobPostId, long userId) {

		UserEntity user = userRepository.findById(userId).orElse(null);

		JobPost jobPost = jobPostRepository.findById(jobPostId).orElse(null);

		if (user != null && jobPost != null) {

			JobApplications jobApplications = new JobApplications();

			jobApplications.setJobPost(jobPost);
			jobApplications.setUser(user);

			jobApplicationRepository.save(jobApplications);
			return new ResponseEntity<String>("done", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

	}

}
