package com.andy.playground.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andy.playground.post.domain.Post;
import com.andy.playground.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/timeline/view")
	public String postList(
			HttpSession session
			, Model model) {
		
		Object userIdObj = session.getAttribute("userId");
		
		if (userIdObj == null) {
			return "redirect:/user/login/view";
		}
		
		long userId = (long) session.getAttribute("userId");
		
		List<Post> postList = postService.getPostList(userId);
		
		model.addAttribute("postList", postList);
		
		return "post/timeline";
		
	}

	@GetMapping("/create/view")
	public String inputPost(HttpSession session) {
		
		if (session.getAttribute("userId") == null) {
			return "redirect:/user/login/view";
		}
		
		return "post/input";
	}


	@GetMapping("/detail/view")
	public String postDetail(@RequestParam("id") long id, Model model) {
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		return "post/detail";

	}

}
