package com.andy.playground.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDto {
	private long userId; // 관련데이터의 프라이머리 키
    private String loginId;

    private String title;
    private String contents;
    private String imagePath;
    private String place;
   
}
