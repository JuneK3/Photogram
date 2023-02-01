package com.rootlab.photogram.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties(value = {"images"})
    private User user;

    @OneToMany(mappedBy = "image")
    @JsonIgnoreProperties(value = {"image"})
    private List<Likes> likes;

    @Transient // DB에 칼럼이 만들어지지 않는다.
    private boolean likeState;

    @Transient
    private int likeCount;

    @OrderBy("id DESC")
    @OneToMany(mappedBy = "image")
    @JsonIgnoreProperties(value = {"image"})
    private List<Comment> comments;

    private LocalDateTime createdAt;

    @PrePersist
    public void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

}