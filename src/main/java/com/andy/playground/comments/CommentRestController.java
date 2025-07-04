package com.andy.playground.comments;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.playground.comments.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post/comment")
@RestController
public class CommentRestController {

	private final CommentService commentService;

	public CommentRestController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/create")
	public Map<String, String> createComment(@RequestParam("postId") int postId,
			@RequestParam("contents") String contents, HttpSession session) {

		Map<String, String> resultMap = new HashMap<>();

		Object userIdObj = session.getAttribute("userId");

		if (userIdObj == null) {
			resultMap.put("result", "fail");
			resultMap.put("message", "User not logged in");
			return resultMap;
		}

		long userId = (long) userIdObj;

		if (commentService.addComment(userId, postId, contents)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}