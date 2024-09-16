package com.blogapplication.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blogapplication.entity.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.UserDto;
import com.blogapplication.repositories.UserRepo;
import com.blogapplication.services.UserService;

@Service
@Component 
class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = userRepo.save(user);
		UserDto userDto1 = userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUSerById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		UserDto userDto1 = userToDto(user);
		return userDto1;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> listUser = userRepo.findAll();
		List<UserDto> dtoLIst = listUser.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		return dtoLIst;
	}

	@Override
	public void deleteUser(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", userId));
		userRepo.delete(user);
	}

	public User dtoToUser(UserDto userDto) {
		 User user = modelMapper.map(userDto, User.class);
//		 user.setId(userDto.getId());
//		 user.setName(userDto.getName());
//		 user.setEmail(userDto.getEmail());
//		 user.setPassword(userDto.getPassword());
//		 user.setAbout(userDto.getAbout());
		 return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
	
}
