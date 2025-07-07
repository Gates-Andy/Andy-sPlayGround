package com.andy.playground.comments.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDto {
    private long loginId;  
    private String text; 
}
