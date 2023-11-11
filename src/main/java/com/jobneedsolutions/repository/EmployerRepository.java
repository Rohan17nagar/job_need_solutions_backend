package com.jobneedsolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobneedsolutions.entity.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer>{

}
