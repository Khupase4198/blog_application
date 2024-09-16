package com.blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.payloads.PostDto;
import com.blogapplication.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId,
			@PathVariable int categoryId){
		PostDto createdPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	
	
	// get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId){
		List<PostDto> postByUser = postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
	}
	
	// get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId){
		List<PostDto> postByCategory = postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postByCategory,HttpStatus.OK);
	}
	
	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> allPosts = postService.getAllPosts();
		return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
	}
	
	// update Post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postId){
		PostDto updatePost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	// get post by id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostByID(@PathVariable int postId){
		PostDto postDto = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);		
	}
	
	
	
	
}
