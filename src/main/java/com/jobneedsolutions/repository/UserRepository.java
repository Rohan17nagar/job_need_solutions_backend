package com.jobneedsolutions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobneedsolutions.entity.UserEntity;
//import com.jwt.springboot.entity.User;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//	UserEntity findByEmail(String email);
	public Optional<UserEntity> findByEmail(String email);
}