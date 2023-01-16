package com.rootlab.photogram.service;

import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }


}
