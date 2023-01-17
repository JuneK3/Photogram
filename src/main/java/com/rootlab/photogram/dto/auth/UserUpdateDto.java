package com.rootlab.photogram.dto.auth;

import com.rootlab.photogram.domain.User;
import lombok.Data;

@Data
public class UserUpdateDto {
    private String name;
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;

    public User toEntity() {
        return User.builder()
                .username(name)
                .password(password)
                .email(website)
                .name(bio)
                .name(phone)
                .name(gender)
                .build();
    }
}
