package com.rootlab.photogram.dto.auth;

import com.rootlab.photogram.domain.User;
import lombok.Data;

@Data
public class SignUpDto {
	private String username;
	private String password;
	private String email;
	private String name;

	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}

}
