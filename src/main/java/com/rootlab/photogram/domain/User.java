package com.rootlab.photogram.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String website; // 웹사이트
    private String bio; // 자기소개

    private String phone;
    private String gender;
    private String profileImageUrl; // 사진
    private String role; // 권한

    private LocalDateTime createdAt;

    @PrePersist // INSERT 직전에 실행
    public void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images;

}
