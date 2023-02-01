package com.rootlab.photogram.controller.api;

import com.rootlab.photogram.config.auth.PrincipalDetails;
import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.CommonResponseDto;
import com.rootlab.photogram.dto.subscribe.SubscribeDto;
import com.rootlab.photogram.dto.user.UserUpdateDto;
import com.rootlab.photogram.service.SubscribeService;
import com.rootlab.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    @PutMapping("/api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(
            @PathVariable Long principalId,
            MultipartFile profileImageFile, // form의 file input id가 profileImageFile이므로 변수명을 동일하게 설정
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = userService.changeUserProfileImage(principalId, profileImageFile);
        principalDetails.setUser(userEntity); // 세션 변경
        return new ResponseEntity<>(new CommonResponseDto<>(1, "프로필사진 변경 성공", null), HttpStatus.OK);
    }

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> getSubscribeList(
            @PathVariable Long pageUserId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<SubscribeDto> dtos = subscribeService.getSubscribeList(principalDetails.getUser().getId(), pageUserId);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "구독자 리스트 가져오기 성공", dtos), HttpStatus.OK);
    }


    @PutMapping("/api/user/{id}")
    public CommonResponseDto<User> update(
            @PathVariable Long id,
            @Valid UserUpdateDto updateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = updateDto.toEntity();
        User updatedUser = userService.updateUser(id, userEntity);
        principalDetails.setUser(updatedUser);
        return new CommonResponseDto<>(1, "회원수정완료", updatedUser);
    }
}
