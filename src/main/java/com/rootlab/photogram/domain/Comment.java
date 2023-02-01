package com.rootlab.photogram.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties(value = {"images"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "imageId")
    private Image image;

    private LocalDateTime createdAt;

    @PrePersist // INSERT 직전에 실행
    public void createdAt() {
        this.createdAt = LocalDateTime.now();
    }
}
