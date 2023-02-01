package com.rootlab.photogram.service;

import com.rootlab.photogram.domain.Comment;
import com.rootlab.photogram.domain.Image;
import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.comment.CommentDto;
import com.rootlab.photogram.handler.exception.CustomApiException;
import com.rootlab.photogram.repository.CommentRepository;
import com.rootlab.photogram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment createComment(CommentDto commentDto, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("유저를 찾을 수 없습니다."));

        // id값만 insert하므로 나머지 값들은 null이어도 상관없음
        Image image = new Image();
        image.setId(commentDto.getImageId());

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .user(user)
                .image(image)
                .build();

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
