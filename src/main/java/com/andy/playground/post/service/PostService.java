package com.andy.playground.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.andy.playground.post.domain.Post;
import com.andy.playground.post.repository.PostRepository;

import common.FileManager;
import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	private final PostRepository postRepository;
	
	public PostService(PostRepository postRepostory) {
		this.postRepository = postRepostory; 
	}
	
	public List<Post> getPostList(long userId) {
		List<Post> postlist = postRepository.findByUserIdOrderByIdDesc(userId);
		return postlist;
	}
	
	public boolean addPost(
			long userId
			, String title
			, String contents
			, MultipartFile file) {
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
				.userId(userId)
				.title(title)
				.contents(contents)
				.imagePath(imagePath)
				.build();
		
		try {
			postRepository.save(post);
		} catch (PersistenceException e) {
			return false;
		}
		return true;

	}
	
	public Post getPost(long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			
			return optionalPost.get();
			
		} else {
			
			return null;
			
		}
	}
}
