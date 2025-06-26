package com.andy.playground.user.service;

import org.springframework.stereotype.Service;

import com.andy.playground.user.repository.UserRepository;

import common.MD5HashingEncoder;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;

	}

	public boolean addUser(String email, String password, String name, String username) {

		String hasgingPassword = MD5HashingEncoder.encode(password);

		int count = userRepository.insertUser(email, hasgingPassword, name, username);

		if (count == 1) {

			return true;

		} else {

			return false;

		}

	}

}
