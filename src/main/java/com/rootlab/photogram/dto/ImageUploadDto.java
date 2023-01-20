package com.rootlab.photogram.dto;

import com.rootlab.photogram.domain.Image;
import com.rootlab.photogram.domain.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {
    private MultipartFile file;
    private String caption;

    public Image toEntity(String imageUrl, User user) {
        return Image.builder()
                .caption(caption)
                .imageUrl(imageUrl)
                .user(user)
                .build();
    }
}
