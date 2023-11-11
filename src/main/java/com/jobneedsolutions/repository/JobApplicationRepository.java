package com.jobneedsolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobneedsolutions.entity.JobApplications;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplications, Integer>{

	
}
