package com.andy.playground.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.andy.playground.post.domain.Post;
import com.andy.playground.post.dto.PostDto;
import com.andy.playground.post.repository.PostRepository;
import com.andy.playground.user.domain.User;
import com.andy.playground.user.service.UserService;

import common.FileManager;
import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	private final PostRepository postRepository;
	private final UserService userService;

	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	public List<PostDto> getPostList() {

		List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

		List<PostDto> dtoList = new ArrayList<>();

		for (Post post : posts) {

			User user = userService.getUserById(post.getLoginid());
			
			PostDto dto = PostDto.builder()
	                .userId(post.getLoginid())
	                .loginId(user.getLoginId())
	                .title(post.getTitle())
	                .contents(post.getContents())
	                .imagePath(post.getImagePath())
	                .place(post.getPlace())
	                .build();
			
			dtoList.add(dto);
		}

		return dtoList;
	}

	public boolean addPost(long loginId, String title, String contents, String place, MultipartFile file) {

		String imagePath = FileManager.saveFile(loginId, file);

		if (imagePath == null) {
			return false;
		}

		Post post = Post.builder().loginid(loginId).title(title).contents(contents).imagePath(imagePath).place(place)
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

		if (optionalPost.isPresent()) {

			return optionalPost.get();

		} else {

			return null;

		}
	}
}
