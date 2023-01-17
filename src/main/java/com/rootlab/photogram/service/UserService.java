package com.rootlab.photogram.service;

import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.auth.UserUpdateDto;
import com.rootlab.photogram.handler.exception.CustomApiException;
import com.rootlab.photogram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User updateUser(Long id, UserUpdateDto userUpdateDto) {
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomApiException("유저정보를 찾을 수 없습니다."));
        userEntity.setName(userUpdateDto.getName());

        String password = userUpdateDto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        userEntity.setPassword(encodedPassword);
        userEntity.setBio(userUpdateDto.getBio());
        userEntity.setWebsite(userUpdateDto.getWebsite());
        userEntity.setPhone(userUpdateDto.getPhone());
        userEntity.setGender(userUpdateDto.getGender());
        User savedUser = userRepository.save(userEntity);
        return savedUser;
    }
}
