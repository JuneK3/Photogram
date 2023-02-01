package com.rootlab.photogram.repository;

import com.rootlab.photogram.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    @Modifying // @Query로 INSERT, UPDATE, DELETE 연산시 필요
    @Query(value = "INSERT INTO SUBSCRIBE(fromUserId, toUserId, createdAt) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

    @Modifying
    @Query(value = "DELETE FROM SUBSCRIBE WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

    @Query(value = "select count(*) from subscribe where fromUserId = :fromUserId", nativeQuery = true)
    Long mSubscribeCount(@Param("fromUserId") Long fromUserId);

    @Query(value = "select count(*) from subscribe where fromUserId = :fromUserId and toUserId = :toUserId", nativeQuery = true)
    Long mSubscribeState(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
}
