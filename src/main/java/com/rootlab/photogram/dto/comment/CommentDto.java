package com.rootlab.photogram.dto.comment;

import lombok.Data;

@Data
public class CommentDto {
    private String content;
    private Long imageId;
}
