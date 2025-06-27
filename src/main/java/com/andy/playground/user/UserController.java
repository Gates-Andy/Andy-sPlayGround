package com.andy.playground.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/user")
@Controller
public class UserController {
	
	@GetMapping("/join/view")
	public String joinInput() {
		
		return "user/join";
		
	}  //post
	
	@GetMapping("/duplicate-id")
	public String duplicateIdInput() {
		
		return "user/duplicate";
		
	}
	
	@GetMapping("/login/view")
	public String loginInput() {
		
		return "user/login";
		
	}  //post
	
	@GetMapping("/post/create")
	public String createTimeline() {
		
		return "user/timeline";
		
	}  //post
	
}
