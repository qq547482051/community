package com.lym.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer parent_id;
    private String content;
    private Integer type;
}
