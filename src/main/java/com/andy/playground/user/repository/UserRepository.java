package com.andy.playground.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.playground.user.domain.User;

@Mapper
public interface UserRepository {
	// 1.회원가입시
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("email") String email);
	
	// 2. 중복아이디체크시
	public int selectCountByloginId(
			@Param("loginId") String loginId);
	
	// 3. 로그인시
	public User selectUser(
			@Param("loginId") String loginId
			, @Param("password") String password);
}
