package com.andy.playground.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andy.playground.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	public List<Post> findByUserIdOrderByIdDesc(long userId);
	
}
