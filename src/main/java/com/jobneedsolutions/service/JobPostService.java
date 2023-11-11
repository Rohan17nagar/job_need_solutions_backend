package com.jobneedsolutions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jobneedsolutions.dto.GetJobPostForUserRequestDto;
import com.jobneedsolutions.dto.JobPostReponseDto;
import com.jobneedsolutions.entity.Employer;
import com.jobneedsolutions.entity.JobPost;
import com.jobneedsolutions.entity.UserEntity;
import com.jobneedsolutions.repository.EmployerRepository;
import com.jobneedsolutions.repository.JobPostRepository;

@Service
public class JobPostService {

	@Autowired
	private JobPostRepository jobPostRepository;

	@Autowired
	private EmployerRepository employerRepository;

	public ResponseEntity<?> getAllJobPosts() {

//		List<JobPost> jobPosts = jobPostRepository.findAll();

		List<JobPostReponseDto> jobPostReponseDtos = jobPostRepository.findAllJobPosts();

		return new ResponseEntity<List<JobPostReponseDto>>(jobPostReponseDtos, HttpStatus.OK);
	}

	public ResponseEntity<?> addJobPost(JobPost jobPost) {

		jobPostRepository.save(jobPost);
		return new ResponseEntity<String>("Job post added successfully.", HttpStatus.OK);
	}

	public ResponseEntity<?> getJobAppsByPostId(int jobPostId) {
		List<UserEntity> users = jobPostRepository.findJobAppsByPostId(jobPostId);
		return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
	}

	public ResponseEntity<?> getJobPostsForUser(long userId, GetJobPostForUserRequestDto getJobPostForUserRequestDto) {
		List<JobPostReponseDto> jj = jobPostRepository.getJobPostsForUser(userId,
				getJobPostForUserRequestDto.getLocation(),getJobPostForUserRequestDto.getCompanyName()

		);

//		System.out.println("hello "+ getJobPostForUserRequestDto.getLocation());

		return new ResponseEntity<List<JobPostReponseDto>>(jj, HttpStatus.OK);

	}
}
