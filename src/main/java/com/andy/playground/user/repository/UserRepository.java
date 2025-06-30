package com.andy.playground.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.andy.playground.user.domain.User;

@Mapper
public interface UserRepository {
	// join
	public int insertUser(@Param("loginId") String loginId, @Param("password") String password,
			@Param("name") String name, @Param("email") String email);
	
	// duplicate
	public int selectCountByloginId(@Param("loginId") String loginId);
	
	// login
	public User selectUser(@Param("loginId") String loginId, @Param("password") String password);
}
