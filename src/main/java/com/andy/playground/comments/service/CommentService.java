package com.andy.playground.comments.service;

import org.springframework.stereotype.Service;

import com.andy.playground.comments.domain.Comment;
import com.andy.playground.comments.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public boolean addComment(long userId, long postId, String contents) {
        try {
            Comment comment = Comment.builder()
                    .userId(userId)
                    .postId(postId)
                    .comment(contents)
                    .build();

            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // logging library 추천
            return false;
        }
    }
}