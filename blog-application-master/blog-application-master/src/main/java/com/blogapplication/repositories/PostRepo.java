package com.blogapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.entity.Category;
import com.blogapplication.entity.Post;
import com.blogapplication.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
}
