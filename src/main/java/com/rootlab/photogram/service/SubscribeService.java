package com.rootlab.photogram.service;

import com.rootlab.photogram.dto.subscribe.SubscribeDto;
import com.rootlab.photogram.handler.exception.CustomApiException;
import com.rootlab.photogram.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

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

    @Transactional(readOnly = true)
    public List<SubscribeDto> getSubscribeList(Long userId, Long pageUserId) {
        // domain entity를 리턴하는 경우가 아니기 때문에
        // SubscribeRepository에 작성해도 사용할 수 없음
        StringBuffer sb = new StringBuffer();
        sb.append("select u.id, u.username, u.profileImageUrl, ");
        sb.append("if ((select 1 from subscribe where fromUserId = ? and toUserId = u.id), 1, 0) subscribeState, ");
        sb.append("if ((select u.id = ?), 1, 0) equalUserState ");
        sb.append("from Users u inner join subscribe s ");
        sb.append("on u.id = s.toUserId ");
        sb.append("where fromUserId = ?");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, userId)
                .setParameter(2, userId)
                .setParameter(3, pageUserId);
        
        // dto에 query 결과를 mapping하기 위해 qlrm 라이브러리가 필요
        JpaResultMapper resultMapper = new JpaResultMapper();
        return resultMapper.list(query, SubscribeDto.class);
    }
}
