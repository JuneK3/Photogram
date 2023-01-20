package com.rootlab.photogram.controller;

import com.rootlab.photogram.config.auth.PrincipalDetails;
import com.rootlab.photogram.dto.ImageUploadDto;
import com.rootlab.photogram.handler.exception.CustomValidationException;
import com.rootlab.photogram.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular() {
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto,
                              @AuthenticationPrincipal PrincipalDetails principalDetails) throws CustomValidationException {

        if(imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }

        imageService.uplodaImage(imageUploadDto, principalDetails);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }

}
