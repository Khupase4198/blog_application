package com.blogapplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.UserDto;
import com.blogapplication.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUSer(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = userService.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("id") int id){
		return ResponseEntity.ok(userService.getUSerById(id));
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUser());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") int id){
		
		UserDto updateUser = userService.updateUser(userDto, id);
		return ResponseEntity.ok(updateUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") int id){
		userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
}
