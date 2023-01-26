package com.rootlab.photogram.repository;

import com.rootlab.photogram.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "select * from image where userId in (select toUserId from subscribe where fromUserId = :principalId)", nativeQuery = true)
    List<Image> mStory(@Param("principalId") Long principalId);
}