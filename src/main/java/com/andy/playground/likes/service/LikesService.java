package com.andy.playground.likes.service;

import org.springframework.stereotype.Service;

import com.andy.playground.likes.domain.Likes;
import com.andy.playground.likes.repository.LikesRepository;

@Service
public class LikesService {

    private final LikesRepository likesRepository;

    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    public boolean addLike(long userId, long postId) {
        try {
            Likes likes = Likes.builder()
                    .userId(userId)
                    .postId(postId)
                    .build();

            likesRepository.save(likes);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // logging 권장
            return false;
        }
    }
}