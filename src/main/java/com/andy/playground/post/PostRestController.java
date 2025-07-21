package com.andy.playground.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.andy.playground.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	private final PostService postService;

	public PostRestController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("/create")
	public Map<String, String> createPost(@RequestParam("title") String title,
			@RequestParam("contents") String contents, @RequestParam("place") String place,
			@RequestParam("imageFile") MultipartFile imageFile, HttpSession session) {

		long loginId = (long) session.getAttribute("userId"); // setAttribute 는 Object

		Map<String, String> resultMap = new HashMap<>();

		if (postService.addPost(loginId, title, contents, place, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}

	@PutMapping("/update")
	public Map<String, String> updatePost(@RequestParam("id") Long postId, @RequestParam("title") String title,
			@RequestParam("contents") String contents, @RequestParam("place") String place,
			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile, HttpSession session) {

		Long loginId = (Long) session.getAttribute("userId");

		Map<String, String> resultMap = new HashMap<>();

		if (postService.updatePost(postId, loginId, title, contents, place, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}

	@DeleteMapping("/delete")
	public Map<String, String> deletePost(@RequestParam("id") long id) {
		Map<String, String> resultMap = new HashMap<>();
		if (postService.deletePost(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}

}
