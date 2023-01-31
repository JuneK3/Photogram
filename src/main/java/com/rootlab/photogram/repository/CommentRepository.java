package com.rootlab.photogram.repository;

import com.rootlab.photogram.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}