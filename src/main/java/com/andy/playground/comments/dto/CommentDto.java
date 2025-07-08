package com.andy.playground.comments.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDto {
	private long id;
	private String text;
	
	private long userId;
	private String loginId;
}
