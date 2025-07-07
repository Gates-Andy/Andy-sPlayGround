package com.andy.playground.post.dto;

import java.util.List;

import com.andy.playground.comments.dto.CommentDto;

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

    private List<CommentDto> comments;
    
}
