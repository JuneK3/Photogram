package com.rootlab.photogram.repository;

import com.rootlab.photogram.domain.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "select * from image where userId in (select toUserId from subscribe where fromUserId = :principalId) order by id desc", nativeQuery = true)
    Page<Image> mStory(@Param("principalId") Long principalId, Pageable pageable);
}