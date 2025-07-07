package com.andy.playground.likes.service;

import org.springframework.stereotype.Service;

import com.andy.playground.likes.domain.Likes;
import com.andy.playground.likes.repository.LikesRepository;

import jakarta.persistence.PersistenceException;

@Service
public class LikesService {

	private final LikesRepository likesRepository;

	public LikesService(LikesRepository likesRepository) {
		this.likesRepository = likesRepository;
	}

	public boolean addLike(long loginId, long postId) {

		Likes likes = Likes.builder()
				.loginId(loginId)
				.postId(postId)
				.build();

		try {
			likesRepository.save(likes);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}
	// 좋아요 갯수 알아내기
	public int likeCountByPostId(long postId) {
		return likesRepository.countByPostId(postId);
	}
	
	// 특정 사용자가 특정 개사물에 좋아요를 눌렀는지
	public boolean isLikePostIdAndUserId(long postId, long loginId) {
		return likesRepository.existsByPostIdAndLoginId(postId, loginId);
	}
}