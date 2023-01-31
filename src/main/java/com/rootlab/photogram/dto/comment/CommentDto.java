package com.rootlab.photogram.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    /**
     * @NotEmpty "", null 체크
     * @NotBlank "", "  ", null 체크
     * @NotNull null 체크
     */

    @NotBlank
    private String content;

    @NotNull
    private Long imageId;
}
