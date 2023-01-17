package com.rootlab.photogram.controller.api;

import com.rootlab.photogram.config.auth.PrincipalUserDetails;
import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.CommonResponseDto;
import com.rootlab.photogram.dto.auth.UserUpdateDto;
import com.rootlab.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CommonResponseDto<User> update(@PathVariable Long id, UserUpdateDto updateDto,
                                          @AuthenticationPrincipal PrincipalUserDetails userDetails) {
//        log.info("[UserApiController] UserUpdateDto: " + updateDto);
        User updateUser = userService.updateUser(id, updateDto);
        userDetails.setUser(updateUser);
        return new CommonResponseDto<>(1, "회원수정완료", updateUser);
    }
}
