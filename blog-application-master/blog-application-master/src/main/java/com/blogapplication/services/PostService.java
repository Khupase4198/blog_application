package com.blogapplication.services;

import java.util.List;

import com.blogapplication.entity.Post;
import com.blogapplication.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, int userId, int categoryId);
	
	PostDto updatePost(PostDto postDto, int postId);
	
	void deletePost(int postId);
	
	List<PostDto> getAllPosts();
	
	PostDto getPostById(int postId);
	
	//	get all post by category
	List<PostDto> getPostByCategory(int categoryId);
	
	//	get all post by user
	List<PostDto> getPostByUser(int userId);
	
	// search post
	List<Post> searchPost(String keyword);
	
}
