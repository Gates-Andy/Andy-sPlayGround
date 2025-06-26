package com.andy.playground.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
	public int insertUser(
			@Param("email") String email
			, @Param("password") String password
			, @Param("name") String name
			, @Param("username") String username);
}
