package com.springboot.practice.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.practice.user.dto.User;
import com.springboot.practice.user.exception.UserNotFoundException;
import com.springboot.practice.user.service.UserManageService;

@RestController
public class UserController {

	@Autowired
	private UserManageService userManageService;

	// Get All users
	@GetMapping("/user")
	public List<User> retriveAllUsers() {
		return this.userManageService.getAllUser();
	}
	
	// Get User by id
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> retriveUser(@PathVariable Integer id) {
		User user = this.userManageService.getUserById(id);
		if(user == null) {
			throw new UserNotFoundException("Id - " + id);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	//Create a user
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = this.userManageService.saveUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	//Delete user
	@DeleteMapping("/delete-user/{id}")
	public User removeUser(@PathVariable Integer id) {
		User deletedUser = this.userManageService.deleteUser(id);
		if(deletedUser == null) {
			throw new UserNotFoundException("Id - " + id);
		}
		return deletedUser;
	}
	

}
