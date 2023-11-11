package com.jobneedsolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobneedsolutions.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
}