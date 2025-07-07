package com.andy.playground.likes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.playground.likes.service.LikesService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class LikesRestController {

	private final LikesService likesService;

	public LikesRestController(LikesService likesService) {
		this.likesService = likesService;
	}

	@PostMapping("/like")
	public Map<String, String> createLike(
			@RequestParam("postId") long postId
			, HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Object userIdObj = session.getAttribute("userId");

		if (userIdObj == null) {
			resultMap.put("result", "fail");
			resultMap.put("message", "User not logged in");
			return resultMap;
		}

		long userId = (long) userIdObj;

		if (likesService.addLike(userId, postId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		return resultMap;
	}
}