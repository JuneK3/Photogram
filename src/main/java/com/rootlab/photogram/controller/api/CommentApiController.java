package com.rootlab.photogram.controller.api;

import com.rootlab.photogram.config.auth.PrincipalDetails;
import com.rootlab.photogram.domain.Comment;
import com.rootlab.photogram.dto.CommonResponseDto;
import com.rootlab.photogram.dto.comment.CommentDto;
import com.rootlab.photogram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> createComment(
            @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Comment comment = commentService.createComment(commentDto, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CommonResponseDto<>(1, "댓글작성 성공", comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comment")
    public ResponseEntity<?> deleteComment() {
        return null;
    }

}
