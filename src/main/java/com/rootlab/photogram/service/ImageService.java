package com.rootlab.photogram.service;

import com.rootlab.photogram.config.auth.PrincipalDetails;
import com.rootlab.photogram.domain.Image;
import com.rootlab.photogram.dto.image.ImageUploadDto;
import com.rootlab.photogram.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void uplodaImage(ImageUploadDto imageUploadDto,
                            PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        String filename = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
        Path path = Paths.get(uploadFolder + filename);

        try {
            Files.write(path, imageUploadDto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = imageUploadDto.toEntity(filename, principalDetails.getUser());
        imageRepository.save(image);
    }

    @Transactional(readOnly = true)
    public Page<Image> getFollowingUserImages(Long principalId, Pageable pageable) {
        Page<Image> images = imageRepository.mStory(principalId, pageable);

        images.forEach(image -> {
            image.setLikeCount(image.getLikes().size());
            image.getLikes().forEach(like -> {
                if (like.getUser().getId().equals(principalId)) {
                    image.setLikeState(true);
                }
            });

        });

        return images;
    }

    public List<Image> getPopularImages() {
        List<Image> images = imageRepository.mPopular();
        return images;
    }
}
