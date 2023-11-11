package com.jobneedsolutions.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jobneedsolutions.entity.UserEntity;
import com.jobneedsolutions.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<List<UserEntity>> getAllUsers() {
		List<UserEntity> users = userRepository.findAll();
		return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
	}

	public ResponseEntity<String> addUser(UserEntity user) {
		try {

			UserEntity userAlreadyExists = userRepository.findByEmail(user.getEmail());
			if (userAlreadyExists != null) {
				throw new RuntimeException("User with email " + user.getEmail() + " already exists");
			}
			userRepository.save(user);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong while adding user", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("User added successfully", HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteUserById(long userId) {

		try {
			Optional<UserEntity> userAlreadyExists = userRepository.findById(userId);

			if (userAlreadyExists.isEmpty()) {
				throw new RuntimeException("User doesn't exist");
			} else {
				userRepository.deleteById(userId);
				return new ResponseEntity<String>("User deleted successfully", HttpStatus.ACCEPTED);
			}
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public ResponseEntity<String> deleteAllUsers() {
		if (userRepository.count() > 0) {
			userRepository.deleteAllInBatch();
			return new ResponseEntity<String>("All users deleted successfully.", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("No users exists to be deleted.", HttpStatus.ACCEPTED);
		}
	}

	public ResponseEntity<?> updateUserById(long userId, UserEntity userEntity) {
		try {
			UserEntity existingUser = userRepository.findById(userId).orElse(null);
			System.out.println("existingUser"+existingUser);
			if(existingUser != null) {
				existingUser.setDob(userEntity.getDob());
				existingUser.setEmail(userEntity.getEmail());
				existingUser.setFirstName(userEntity.getFirstName());
				existingUser.setGender(userEntity.getGender());
				existingUser.setLastName(userEntity.getLastName());
				existingUser.setPassword(userEntity.getPassword());
				existingUser.setPhone(userEntity.getPhone());
				
				userRepository.save(existingUser);
				return new ResponseEntity<UserEntity>(existingUser,HttpStatus.OK);
			}else {
				throw new RuntimeException("No user exist with id"+userId);
			}
		}catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
