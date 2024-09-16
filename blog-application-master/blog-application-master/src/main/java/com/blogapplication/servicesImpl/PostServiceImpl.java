package com.blogapplication.servicesImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.entity.Category;
import com.blogapplication.entity.Post;
import com.blogapplication.entity.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.PostDto;
import com.blogapplication.repositories.CategoryRepo;
import com.blogapplication.repositories.PostRepo;
import com.blogapplication.repositories.UserRepo;
import com.blogapplication.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Post post = dtoToPost(postDto);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = postRepo.save(post);
		return postToDto(newPost) ;
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		Post savedPost = postRepo.save(post);
		return postToDto(savedPost);
	}

	@Override
	public void deletePost(int postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> findAll = postRepo.findAll();
		List<PostDto> allPostsDto = findAll.stream().map(posts -> postToDto(posts)).collect(Collectors.toList());
		return allPostsDto;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		return postToDto(post);
	}

	@Override
	public List<PostDto> getPostByCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> listOfPost = postRepo.findByCategory(category);
		List<PostDto> listOfPOstDto = listOfPost.stream().map(post ->postToDto(post)).collect(Collectors.toList());
		return listOfPOstDto;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id", userId));
		List<Post> listOfPost = postRepo.findByUser(user);
		List<PostDto> listOfPostDto = listOfPost.stream().map(use -> postToDto(use)).collect(Collectors.toList());
		return listOfPostDto;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	public PostDto postToDto(Post post) {
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
	}
	
	public Post dtoToPost(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		return post;
	}
}
