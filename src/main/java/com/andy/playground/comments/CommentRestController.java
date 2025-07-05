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
// @RestController: 이 클래스는 REST API 요청을 처리하는 컨트롤러라는 의미입니다.
// @Controller + @ResponseBody 역할을 동시에 합니다. 반환값이 HTML이 아닌 JSON 형태로 응답됩니다 
public class CommentRestController {

	private final CommentService commentService;

	public CommentRestController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/create")
	public Map<String, String> createComment(
			@RequestParam("postId") int postId
			, @RequestParam("comment") String comment
			, HttpSession session) {
		
		Object userIdObj = (Long)session.getAttribute("userId");
		//	HttpSession의 getAttribute() 메서드는 항상 Object 타입으로 반환합니다.
		//	이유는: 세션에는 어떤 타입이든 저장할 수 있기 때문이에요. (String, Long, User, 등등 다 가능)
		// Object 타입은 모든 타입의 부모지만, 그냥 long userId = session.getAttribute("userId"); 
		// 처럼 직접 쓰면 에러가 나요.왜냐면 Java는 컴파일 시점에 타입을 정확히 알아야 하기 때문이에요.
		Map<String, String> resultMap = new HashMap<>();

		if (userIdObj == null) {
			resultMap.put("result", "fail");
			resultMap.put("message", "User not logged in");
			return resultMap;
		}

		long userId = (long) userIdObj;

		if (commentService.addComment(userId, postId, comment)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}