package com.rootlab.photogram.service;

import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.handler.exception.CustomApiException;
import com.rootlab.photogram.handler.exception.CustomException;
import com.rootlab.photogram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User updateUser(Long id, User user) {
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomApiException("유저정보를 찾을 수 없습니다."));
        userEntity.setName(user.getName());

        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        userEntity.setPassword(encodedPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        User savedUser = userRepository.save(userEntity);
        return savedUser;
    }

    @Transactional(readOnly = true) // 더티체킹x
    public User getUserProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("해당 프로필 페이지는 없는 페이지입니다."));
        return user;
    }
}
