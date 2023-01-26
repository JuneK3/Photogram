package com.rootlab.photogram.controller.api;

import com.rootlab.photogram.config.auth.PrincipalDetails;
import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.CommonResponseDto;
import com.rootlab.photogram.dto.subscribe.SubscribeDto;
import com.rootlab.photogram.dto.user.UserUpdateDto;
import com.rootlab.photogram.handler.exception.CustomValidationApiException;
import com.rootlab.photogram.service.SubscribeService;
import com.rootlab.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    private final SubscribeService subscribeService;

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> getSubscribeList(@PathVariable Long pageUserId,
                                              @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<SubscribeDto> dtos = subscribeService.getSubscribeList(principalDetails.getUser().getId(), pageUserId);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "구독자 리스트 가져오기 성공", dtos), HttpStatus.OK);
    }


    @PutMapping("/api/user/{id}")
    public CommonResponseDto<User> update(
            @PathVariable Long id,
            @Valid UserUpdateDto updateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) throws CustomValidationApiException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패", errorMap);
        }

        User userEntity = updateDto.toEntity();
        User updatedUser = userService.updateUser(id, userEntity);
        principalDetails.setUser(updatedUser);
        return new CommonResponseDto<>(1, "회원수정완료", updatedUser);
    }
}
