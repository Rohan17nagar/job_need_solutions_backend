package com.jobneedsolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobneedsolutions.entity.JobCategory;

public interface JobCategoryRepository  extends JpaRepository<JobCategory,Integer>{

}
