package com.rootlab.photogram.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String password;
	private String name;
	private String website; // 웹사이트
	private String bio; // 자기소개
	private String email;
	private String phone;
	private String gender;
	private String profileImageUrl; // 사진
	private String role; // 권한

	private LocalDateTime createdAt;

	@PrePersist // INSERT 직전에 실행
	public void createdAt() {
		this.createdAt = LocalDateTime.now();
	}
}
