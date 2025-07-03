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

	public List<Post> getPostList(long loginId) {
		List<Post> postlist = postRepository.findByloginidOrderByIdDesc(loginId);
		return postlist;
	}

	public boolean addPost(long loginId, String title, String contents, String location,
			MultipartFile file) {

		String imagePath = FileManager.saveFile(loginId, file);

		if (imagePath == null) {
			return false;
		}

		Post post = Post.builder().loginid(loginId).title(title).contents(contents).imagePath(imagePath)
				.location(location).build();

		try {
			postRepository.save(post);
		} catch (PersistenceException e) {
			return false;
		}
		return true;

	}

	public Post getPost(long id) {
		Optional<Post> optionalPost = postRepository.findById(id);

		if (optionalPost.isPresent()) {

			return optionalPost.get();

		} else {

			return null;

		}
	}
}
