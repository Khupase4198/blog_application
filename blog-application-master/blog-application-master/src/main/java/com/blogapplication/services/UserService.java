package com.blogapplication.services;

import java.util.List;

import com.blogapplication.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUSerById(Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(int userId);

}
