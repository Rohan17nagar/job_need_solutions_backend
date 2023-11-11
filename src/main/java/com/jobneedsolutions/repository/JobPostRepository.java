package com.jobneedsolutions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobneedsolutions.dto.JobPostReponseDto;
import com.jobneedsolutions.entity.JobPost;
import com.jobneedsolutions.entity.UserEntity;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

//	@Query(nativeQuery = true,value = "select DISTINCT u.* from user_entity u JOIN job_applications ja ON u.user_id = ja.user_id where ja.job_post_id = :jobPostId")
	@Query("SELECT DISTINCT u FROM UserEntity u JOIN u.jobApplications ja WHERE ja.jobPost.jobId = :jobId")
	List<UserEntity> findJobAppsByPostId(@Param("jobId") int jobId);

	@Query("SELECT NEW com.jobneedsolutions.dto.JobPostReponseDto(jp.jobId,jp.jobTitle,jp.jobDescription,jp.location,jp.salaryRange,jp.experienceRange,jp.applicationDeadline,jp.openings,jp.skillsRequired,c.companyName, (SELECT COUNT(ja) FROM JobApplications ja WHERE ja.jobPost = jp),jp.educationRequired,jp.employementType) FROM JobPost jp JOIN jp.employer c")
	List<JobPostReponseDto> findAllJobPosts();
	
//	@Query("SELECT NEW com.jobneedsolutions.dto.JobPostReponseDto(jp.jobId,jp.jobTitle,jp.jobDescription,jp.location,jp.salaryRange,jp.experienceRange,jp.applicationDeadline,jp.openings,jp.skillsRequired,c.companyName, (SELECT COUNT(ja) FROM JobApplications ja WHERE ja.jobPost = jp),jp.educationRequired,jp.employementType) FROM JobPost jp LEFT JOIN jp.jobApplications ja WITH ja.user.userId= :userId  WHERE ja.jobApplicationId IS NULL AND (:location IS NULL OR jp.location = :location)")
	@Query("SELECT NEW com.jobneedsolutions.dto.JobPostReponseDto(jp.jobId,jp.jobTitle,jp.jobDescription,jp.location,jp.salaryRange,jp.experienceRange,jp.applicationDeadline,jp.openings,jp.skillsRequired,e.companyName, (SELECT COUNT(ja) FROM JobApplications ja WHERE ja.jobPost = jp),jp.educationRequired,jp.employementType) FROM JobPost jp LEFT JOIN jp.jobApplications ja WITH ja.user.userId= :userId JOIN employer e WHERE ja.jobApplicationId IS NULL AND (:location IS NULL OR jp.location = :location) AND (:companyName IS NULL OR e.companyName = :companyName)")
	
	List<JobPostReponseDto> getJobPostsForUser(@Param("userId") long userId,@Param("location") String location,@Param("companyName") String companyName);
	
}
