package com.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
