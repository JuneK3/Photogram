package com.rootlab.photogram.repository;

import com.rootlab.photogram.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "insert into likes(imageId, userId, createdAt) values(:imageId, :userId, now())", nativeQuery = true)
    void mLikes(@Param("imageId") Long imageId, @Param("userId") Long userId);

    @Modifying
    @Query(value = "delete from likes where imageId = :imageId and userId = :userId", nativeQuery = true)
    void mDislikes(@Param("imageId") Long imageId, @Param("userId") Long userId);

}
