package com.andy.playground.post.dto;

import java.util.List;

import com.andy.playground.comments.domain.Comment;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDto {
	private long id;
	private long userId; // 관련데이터의 프라이머리 키
    private String loginId;

    private String title;
    private String contents;
    private String imagePath;
    private String place;
    
    private long likeCount; 
    private boolean isLike;
    
    // 댓글 목록
    private List<Comment> commentList;
    
}
