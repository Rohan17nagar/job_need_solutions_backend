package com.jobneedsolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobneedsolutions.entity.UserEntity;
import com.jobneedsolutions.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserEntity userEntity){
		return userService.addUser(userEntity);
	}
	
	@DeleteMapping("/deleteUserById/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable int userId){
		return userService.deleteUserById(userId);
	}
	
	
	@DeleteMapping("/deleteAllUsers")
	public ResponseEntity<String> deleteAllUsers(){
		return userService.deleteAllUsers();
	}
	
	@PutMapping("/updateUserById/{userId}")
	public ResponseEntity<?> updateUserById(@PathVariable long userId,@RequestBody UserEntity userEntity){
		return userService.updateUserById(userId,userEntity);
	}	
}
