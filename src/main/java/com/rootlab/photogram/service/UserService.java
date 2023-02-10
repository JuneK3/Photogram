package com.rootlab.photogram.service;

import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.user.UserProfileDto;
import com.rootlab.photogram.handler.exception.CustomApiException;
import com.rootlab.photogram.handler.exception.CustomException;
import com.rootlab.photogram.repository.SubscribeRepository;
import com.rootlab.photogram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public User updateUser(Long id, User user) {
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomApiException("유저정보를 찾을 수 없습니다."));
        userEntity.setName(user.getName());

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        userEntity.setPassword(encodedPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        return userRepository.save(userEntity);
    }

    @Transactional(readOnly = true) // 더티체킹x
    public UserProfileDto getUserProfile(Long pageUserId, Long principalId) {
        User user = userRepository.findById(pageUserId).orElseThrow(() -> new CustomException("해당 프로필 페이지는 없는 페이지입니다."));
        Long subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        Long subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);

        UserProfileDto userProfileDto = UserProfileDto.builder()
                .pageOwnerState(pageUserId.equals(principalId))
                .imageCount(user.getImages().size())
                .user(user)
                .subscribeState(subscribeState == 1)
                .subscribeCount(subscribeCount)
                .build();

        user.getImages().forEach(image -> image.setLikeCount(image.getLikes().size()));

        return userProfileDto;
    }

    @Transactional
    public User changeUserProfileImage(Long principalId, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        User userEntity = userRepository.findById(principalId)
                .orElseThrow(() -> new CustomApiException("유저를 찾을 수 없습니다."));
        userEntity.setProfileImageUrl(imageFileName);

        return userEntity;
    }
}
