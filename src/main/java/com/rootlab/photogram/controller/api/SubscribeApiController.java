package com.rootlab.photogram.controller.api;

import com.rootlab.photogram.config.auth.PrincipalDetails;
import com.rootlab.photogram.dto.CommonResponseDto;
import com.rootlab.photogram.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscribe")
@Slf4j
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId) {
        log.info("[SubscribeApiController] subscribe 실행됨");
        subscribeService.subscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "구독 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId) {
        log.info("[SubscribeApiController] unSubscribe 실행됨");
        subscribeService.unSubscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "구독 취소", null), HttpStatus.OK);
    }

}
