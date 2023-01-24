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
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private LocalDateTime createdAt;

    @PrePersist
    public void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

}