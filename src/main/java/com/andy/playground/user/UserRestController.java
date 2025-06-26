package com.andy.playground.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andy.playground.user.service.UserService;

@RequestMapping("/user")
@RestController
public class UserRestController {
	private final UserService userService;

	public UserRestController(UserService userService) {
		
		this.userService = userService;
		
	}

	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("email") String email
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("username") String username) {

		Map<String, String> resultMap = new HashMap<>();

		if (userService.addUser(email, name, password, username)) {
			
			resultMap.put("result", "success");
			
		} else {
			
			resultMap.put("result", "fail");
			
		}

		return resultMap;

	}

}
