package com.andy.playground.user.service;

import org.springframework.stereotype.Service;

import com.andy.playground.user.domain.User;
import com.andy.playground.user.repository.UserRepository;

import common.MD5HashingEncoder;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// 1. 회원가입
	public boolean addUser(
			String loginId
			, String password
			, String name
			, String email) {

		String hasgingPassword = MD5HashingEncoder.encode(password);

		int count = userRepository.insertUser(
				loginId
				, hasgingPassword
				, name
				, email);

		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	// 2. 중복확인
	public boolean isDuplicateId(
			String loginId) {

		int count = userRepository.selectCountByloginId(loginId);
		
		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}

	// 3. login
	public User getUser(
			String loginId
			, String password) {

		String hashingPassword = MD5HashingEncoder.encode(password);

		return userRepository.selectUser(loginId, hashingPassword);

	}
}
