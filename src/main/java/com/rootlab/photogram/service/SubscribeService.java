package com.rootlab.photogram.service;

import com.rootlab.photogram.handler.exception.CustomApiException;
import com.rootlab.photogram.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void subscribe(Long fromUserId, Long toUserId) {
        if (fromUserId.equals(toUserId)) {
            throw new CustomApiException("유저 자신을 구독할 수 없습니다.");
        }
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독하였습니다.");
        }
    }

    @Transactional
    public void unSubscribe(Long fromUserId, Long toUserId) {
        if (fromUserId.equals(toUserId)) {
            throw new CustomApiException("유저 자신을 구독 취소할 수 없습니다.");
        }
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
