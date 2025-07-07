package com.andy.playground.likes.repository;

import com.andy.playground.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
	
	// SELEC count(*) FROM `likes` WHERE `postId` = 4;
	public int countByPostId(long postId);
	
	// JPA 메서드 규격 existsBy
	public boolean existsByPostIdAndLoginId(long postId, long loginId);
}