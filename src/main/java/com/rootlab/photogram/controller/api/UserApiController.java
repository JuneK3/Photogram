package com.rootlab.photogram.controller.api;

import com.rootlab.photogram.config.auth.PrincipalDetails;
import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.CommonResponseDto;
import com.rootlab.photogram.dto.user.UserUpdateDto;
import com.rootlab.photogram.handler.exception.CustomValidationApiException;
import com.rootlab.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CommonResponseDto<User> update(
            @PathVariable Long id,
            @Valid UserUpdateDto updateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails userDetails
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
        userDetails.setUser(updatedUser);
        return new CommonResponseDto<>(1, "회원수정완료", updatedUser);
    }
}
