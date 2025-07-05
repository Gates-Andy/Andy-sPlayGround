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

	public boolean addLike(
			long userId
			, long postId) {

		Likes likes = Likes.builder()
		.userId(userId)
		.postId(postId)
		.build();

		try {
			likesRepository.save(likes);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}
}